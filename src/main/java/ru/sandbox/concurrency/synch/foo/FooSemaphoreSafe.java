package ru.sandbox.concurrency.synch.foo;

import java.util.concurrent.Semaphore;

public class FooSemaphoreSafe {
    private final Semaphore betweenFirstAndSecond = new Semaphore(0);
    private final Semaphore betweenSecondAndThird = new Semaphore(0);

    public void first() {
        System.out.println("First");
        betweenFirstAndSecond.release();
    }

    public void second() {
        try {
            betweenFirstAndSecond.acquire();
            System.out.println("Second");
            betweenSecondAndThird.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void third() {
        try {
            betweenSecondAndThird.acquire();
            System.out.println("Third");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
