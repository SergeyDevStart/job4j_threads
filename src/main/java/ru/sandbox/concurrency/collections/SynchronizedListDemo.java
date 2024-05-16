package ru.sandbox.concurrency.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListDemo {
    public static void main(String[] args) {
        List<Integer> threadUnSavelist = Collections.synchronizedList(new ArrayList<>());
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                threadUnSavelist.add(i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                threadUnSavelist.add(i);
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Size = " + threadUnSavelist.size());
    }
}
