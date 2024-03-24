package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int totalSize;

    public SimpleBlockingQueue(int totalSize) {
        this.totalSize = totalSize;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() >= totalSize) {
            wait();
        }
        queue.offer(value);
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T value = queue.poll();
        notify();
        return value;
    }
}
