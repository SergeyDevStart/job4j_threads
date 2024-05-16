package ru.sandbox.concurrency.collections;

import java.util.concurrent.BlockingQueue;

public class WriteForPriorityQueueWorker implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public WriteForPriorityQueueWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put(5);
            blockingQueue.put(6);
            blockingQueue.put(10);
            blockingQueue.put(1);
            blockingQueue.put(7);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
