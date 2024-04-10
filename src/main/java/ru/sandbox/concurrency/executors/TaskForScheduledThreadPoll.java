package ru.sandbox.concurrency.executors;

import java.util.Random;

public class TaskForScheduledThreadPoll implements Runnable {
    private final Random random = new Random();
    private final int id;

    public TaskForScheduledThreadPoll(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        Integer value = random.nextInt(100);
        System.out.printf("Task id: %d, generate value: %d%n", id, value);
    }
}
