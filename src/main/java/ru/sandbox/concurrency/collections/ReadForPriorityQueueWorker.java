package ru.sandbox.concurrency.collections;

import java.util.concurrent.BlockingQueue;

public class ReadForPriorityQueueWorker implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public ReadForPriorityQueueWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
