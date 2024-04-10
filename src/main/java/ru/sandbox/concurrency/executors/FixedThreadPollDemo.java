package ru.sandbox.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPollDemo {
    public static void main(String[] args) throws InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();
        long start = System.currentTimeMillis();
        ExecutorService poll = Executors.newFixedThreadPool(cores);
        for (int i = 0; i < 100; i++) {
            poll.submit(new Task());
        }
        poll.shutdown();
        while (!poll.isTerminated()) {
            Thread.sleep(1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("Total execution time: %d%n", (end - start));
    }
}
