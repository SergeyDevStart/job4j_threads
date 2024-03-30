package ru.sandbox.concurrency.synch.semafore;

public class PrintInOrderDemo {
    public static void main(String[] args) {
        PrintInOrder printInOrder = new PrintInOrder();
        Thread thread1 = new Thread(printInOrder::first, "T1");
        Thread thread2 = new Thread(printInOrder::second, "T2");
        Thread thread3 = new Thread(printInOrder::third, "T3");
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("END");
    }
}
