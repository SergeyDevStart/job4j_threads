package ru.sandbox.concurrency.collections;

import java.util.concurrent.BlockingQueue;

public class TakeForBlockingQueueWorker implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public TakeForBlockingQueueWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int result = blockingQueue.take();
                System.out.printf("Take: %d%n", result);
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
