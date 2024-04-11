package ru.sandbox.concurrency.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SumCalculator {
    private static final long RANGE = 1_000_000_000L;
    private static long sumRange = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Long>> futureList = new ArrayList<>();
        long rangeOfTask = RANGE / 10;
        for (int i = 0; i < 10; i++) {
            long from = rangeOfTask * i + 1;
            long to = rangeOfTask * (i + 1);
            PartSum partSum = new PartSum(from, to);
            futureList.add(executorService.submit(partSum));
        }
        for (Future<Long> future : futureList) {
            sumRange += future.get();
        }
        executorService.shutdown();
        System.out.println(sumRange);
    }
}

class PartSum implements Callable<Long> {
    private final long from;
    private final long to;
    private long localResult;

    public PartSum(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public Long call() {
        for (long i = from; i <= to; i++) {
            localResult += i;
        }
        System.out.println("Performs addition from: " + from + " to: " + to);
        return localResult;
    }
}
