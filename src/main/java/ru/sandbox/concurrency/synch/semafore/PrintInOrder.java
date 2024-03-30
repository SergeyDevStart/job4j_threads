package ru.sandbox.concurrency.synch.semafore;

import java.util.concurrent.Semaphore;

public class PrintInOrder {
    private final Semaphore betweenFirstAndSecond = new Semaphore(0);
    private final Semaphore betweenSecondAndThird = new Semaphore(0);

    public void first() {
        try {
            System.out.println("FIRST");
            Thread.sleep(500);
            betweenFirstAndSecond.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void second() {
        try {
            betweenFirstAndSecond.acquire();
            System.out.println("SECOND");
            Thread.sleep(500);
            betweenSecondAndThird.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void third() {
        try {
            betweenSecondAndThird.acquire();
            System.out.println("THIRD");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
