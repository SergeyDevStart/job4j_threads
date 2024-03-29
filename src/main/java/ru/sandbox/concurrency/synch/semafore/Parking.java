package ru.sandbox.concurrency.synch.semafore;

import java.util.concurrent.Semaphore;

public class Parking {
    private static final boolean[] PARKING_PLACES = new boolean[5];
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        for (int index = 1; index < 10; index++) {
            new Thread(new Car(index)).start();
            Thread.sleep(1000);
        }
    }

    public static class Car implements Runnable {
        private final int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Автомобиль №%d подъехал к парковке.\n", carNumber);
                SEMAPHORE.acquire();
                int numberPlaces = -1;
                synchronized (PARKING_PLACES) {
                    for (int index = 0; index < 5; index++) {
                        if (!PARKING_PLACES[index]) {
                            PARKING_PLACES[index] = true;
                            numberPlaces = index;
                            System.out.printf("Автомобиль №%d припарковался в месте №%d.\n", carNumber, numberPlaces);
                            break;
                        }
                    }
                }
                Thread.sleep(5000);
                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[numberPlaces] = false;
                }
                System.out.printf("Автомобиль №%d покинул парковку.\n", carNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                SEMAPHORE.release();
            }
        }
    }
}
