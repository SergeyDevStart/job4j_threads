package ru.job4j.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool;

    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void emailTo(User user) {
        pool.submit(() -> {
           String subject = String.format("Notification {%s} to email {%s}.", user.name(), user.email());
           String body = String.format("Add a new event to {%s}", user.name());
           send(subject, body, user.email());
        });
    }

    public void send(String subject, String body, String email) { }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
