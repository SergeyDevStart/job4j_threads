package ru.sandbox.concurrency.callable;

import java.util.concurrent.*;

public class FactorialDemo {
    static int totalValue;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> result = executorService.submit(new Factorial(6));
        try {
            totalValue = result.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
        System.out.println(totalValue);
    }
}

class Factorial implements Callable<Integer> {
    private final int value;

    public Factorial(int value) {
        this.value = value;
    }

    @Override
    public Integer call() {
        if (value <= 0) {
            throw new IllegalStateException();
        }
        int result = 1;
        for (int i = 1; i <= value; i++) {
            result *= i;
        }
        return result;
    }
}
