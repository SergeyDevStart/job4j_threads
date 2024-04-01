package ru.job4j.cas;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CasCountTest {
    @Test
    public void whenIncrementThenSuccessfully() throws InterruptedException {
        CasCount casCount = new CasCount();
        Thread thread1 = new Thread(casCount::increment);
        Thread thread2 = new Thread(casCount::increment);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(casCount.get()).isEqualTo(2);
    }

    @Test
    public void whenTwoThreadIncrement100Then200() throws InterruptedException {
        CasCount casCount = new CasCount();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                casCount.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                casCount.increment();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(casCount.get()).isEqualTo(200);
    }
}