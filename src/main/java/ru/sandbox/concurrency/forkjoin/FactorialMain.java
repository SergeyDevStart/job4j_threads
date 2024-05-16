package ru.sandbox.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class FactorialMain {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long result = forkJoinPool.invoke(new FactorialTask(10));
        System.out.println(result);
    }
}
