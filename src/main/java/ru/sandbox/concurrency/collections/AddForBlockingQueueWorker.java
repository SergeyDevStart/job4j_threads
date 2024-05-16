package ru.sandbox.concurrency.collections;

import java.util.concurrent.BlockingQueue;

public class AddForBlockingQueueWorker implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public AddForBlockingQueueWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.printf("Put: %d%n", count);
                blockingQueue.put(count++);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
