package ru.sandbox.concurrency.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SynchronizedQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(100);
        Thread thread1 = new Thread(new AddForBlockingQueueWorker(blockingQueue));
        Thread thread2 = new Thread(new TakeForBlockingQueueWorker(blockingQueue));
        thread1.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(3000);
        thread1.interrupt();
        thread2.interrupt();
    }
}
