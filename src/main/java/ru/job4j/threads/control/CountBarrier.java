package ru.job4j.threads.control;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            System.out.println("Count: " + count);
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + " did the job.");
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(3);
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                countBarrier.count();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                new Thread(countBarrier::await).start();
            }
        }).start();
    }
}
