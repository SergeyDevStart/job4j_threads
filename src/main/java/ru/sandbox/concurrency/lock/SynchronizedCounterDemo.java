package ru.sandbox.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedCounterDemo {
    private static int count = 0;
    private static final Lock LOCK = new ReentrantLock();

    public static void increment() {
        LOCK.lock();
        count++;
        LOCK.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                increment();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
