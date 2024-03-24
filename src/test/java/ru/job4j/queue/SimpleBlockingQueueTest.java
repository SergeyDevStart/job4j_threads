package ru.job4j.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {
    @Test
    public void whenOffer3ValueAndPool3ValueThenSuccessfully() throws InterruptedException {
        var queue = new SimpleBlockingQueue<Integer>(3);
        List<Integer> result = new ArrayList<>();
        Thread producer = new Thread(() -> {
            for (int index = 0; index < 3; index++) {
                try {
                    queue.offer(index);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread consumer = new Thread(() -> {
            for (int index = 0; index < 3; index++) {
                try {
                    result.add(queue.poll());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(result).containsExactly(0, 1, 2);
    }
}