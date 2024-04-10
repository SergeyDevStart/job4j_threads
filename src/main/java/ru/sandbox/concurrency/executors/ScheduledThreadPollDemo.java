package ru.sandbox.concurrency.executors;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPollDemo {
    public static void main(String[] args) throws InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();
        long start = System.currentTimeMillis();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(cores);
        TaskForScheduledThreadPoll task1 = new TaskForScheduledThreadPoll(1);
        TaskForScheduledThreadPoll task2 = new TaskForScheduledThreadPoll(2);
        TaskForScheduledThreadPoll task3 = new TaskForScheduledThreadPoll(3);
        TaskForScheduledThreadPoll task4 = new TaskForScheduledThreadPoll(4);
        TaskForScheduledThreadPoll task5 = new TaskForScheduledThreadPoll(5);
        executorService.schedule(task5, 0, TimeUnit.SECONDS);
        executorService.schedule(task4, 2, TimeUnit.SECONDS);
        executorService.schedule(task3, 4, TimeUnit.SECONDS);
        executorService.schedule(task2, 6, TimeUnit.SECONDS);
        executorService.schedule(task1, 10, TimeUnit.SECONDS);
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("Total execution time: %d%n", (end - start));
    }
}
