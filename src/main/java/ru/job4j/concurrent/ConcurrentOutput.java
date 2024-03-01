package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(
                    () -> System.out.println(Thread.currentThread().getName())
            );
            thread.start();
        }
    }
}
