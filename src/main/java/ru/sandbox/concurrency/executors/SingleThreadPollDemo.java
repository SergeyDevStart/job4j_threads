package ru.sandbox.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPollDemo {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Task());
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("Total execution time: %d%n", (end - start));
    }
}
