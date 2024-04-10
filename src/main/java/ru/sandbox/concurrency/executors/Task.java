package ru.sandbox.concurrency.executors;

import java.util.Random;

public class Task implements Runnable {
    Random random = new Random();

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            int value = random.nextInt(1000);
            System.out.printf("Print value: %d%n", value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
