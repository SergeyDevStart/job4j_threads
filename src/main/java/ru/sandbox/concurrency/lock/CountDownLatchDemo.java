package ru.sandbox.concurrency.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Market market = new Market(countDownLatch);
        Person newPerson1 = new Person("A", countDownLatch);
        Person newPerson2 = new Person("B", countDownLatch);
        Person newPerson3 = new Person("C", countDownLatch);
        Person newPerson4 = new Person("D", countDownLatch);
        try {
            market.marketStaffIsOnPlace();
            market.everythingIsReady();
            market.openMarket();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Market {
    private final CountDownLatch countLatch;

    public Market(CountDownLatch countLatch) {
        this.countLatch = countLatch;
    }

    public void marketStaffIsOnPlace() throws InterruptedException {
        Thread.sleep(2000);
        countLatch.countDown();
        System.out.println("market staff is on place" + countLatch.getCount());
    }

    public void openMarket() throws InterruptedException {
        Thread.sleep(3000);
        countLatch.countDown();
        System.out.println("open market" + countLatch.getCount());
    }

    public void everythingIsReady() throws InterruptedException {
        Thread.sleep(4000);
        countLatch.countDown();
        System.out.println("everything is ready" + countLatch.getCount());
    }
}

class Person extends Thread {
    private final String name;
    private final CountDownLatch countDownLatch;

    Person(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
        this.start();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println(name + "приступил к покупкам");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
