package ru.sandbox.concurrency.synch.foo;

import java.util.Arrays;
import java.util.List;

public class FooSemaphoreSafeDemo {
    public static void main(String[] args) throws InterruptedException {
        FooSemaphoreSafe semaphoreSafe = new FooSemaphoreSafe();
        Thread thread1 = new Thread(semaphoreSafe::first, "First");
        Thread thread2 = new Thread(semaphoreSafe::second, "Second");
        Thread thread3 = new Thread(semaphoreSafe::third, "Third");
        List<Thread> threads = Arrays.asList(thread1, thread2, thread3);
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
