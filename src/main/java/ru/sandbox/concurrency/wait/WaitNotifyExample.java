package ru.sandbox.concurrency.wait;

public class WaitNotifyExample {
    public static void main(String[] args) {
        Market market = new Market();
        Thread producer = new Thread(new Producer(market));
        Thread consumer = new Thread(new Consumer(market));
        producer.start();
        consumer.start();
    }
}

class Market {
    private int countProduct = 0;

    public synchronized void putProduct() throws InterruptedException {
        while (countProduct >= 5) {
            wait();
        }
        countProduct++;
        Thread.sleep(500);
        System.out.println("Producer added product. Product quantity: " + countProduct);
        notify();
    }

    public synchronized void getProduct() throws InterruptedException {
        while (countProduct < 1) {
            wait();
        }
        countProduct--;
        Thread.sleep(500);
        System.out.println("Consumer bought the product. Product quantity: " + countProduct);
        notify();
    }
}

class Consumer implements Runnable {
    private final Market market;

    public Consumer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for (int index = 0; index < 10; index++) {
            try {
                market.getProduct();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}

class Producer implements Runnable {
    private final Market market;

    public Producer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for (int index = 0; index < 10; index++) {
            try {
                market.putProduct();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
