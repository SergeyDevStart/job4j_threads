package ru.sandbox.concurrency.collections;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
        System.out.println(map);
        Thread readThread = new Thread(() -> {
            Iterator<Integer> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int key = iterator.next();
                System.out.printf("result: key: %d = value: %s%n", key, map.get(key));
            }
        });
        Thread writeThread = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            map.put(6, "F");
        });
        readThread.start();
        writeThread.start();
        readThread.join();
        writeThread.join();
        System.out.println(map);
    }
}
