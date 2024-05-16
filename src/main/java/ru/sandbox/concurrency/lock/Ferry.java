package ru.sandbox.concurrency.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Ferry {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoard());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    private static class FerryBoard implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("Паром переправил автомобили!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Car implements Runnable {
        private final int id;

        public Car(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Автомобиль №%d подъехал к переправе%n", id);
                BARRIER.await();
                System.out.printf("Автомобиль №%d продолжил движение%n", id);
            } catch (BrokenBarrierException | InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}