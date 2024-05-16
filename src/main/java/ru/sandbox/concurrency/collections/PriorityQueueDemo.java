package ru.sandbox.concurrency.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<>();
        Thread thread1 = new Thread(new WriteForPriorityQueueWorker(blockingQueue));
        Thread thread2 = new Thread(new ReadForPriorityQueueWorker(blockingQueue));
        thread1.start();
        thread2.start();
    }
}
