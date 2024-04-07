package ru.sandbox.concurrency.worker;

public class Task implements Runnable {
    private final int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.printf(Thread.currentThread().getName() + " Task № %d started.\n", id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.printf(Thread.currentThread().getName() + " Task № %d finished.\n", id);
    }
}
