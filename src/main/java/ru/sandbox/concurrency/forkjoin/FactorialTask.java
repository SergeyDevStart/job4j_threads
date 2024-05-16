package ru.sandbox.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final long i;

    public FactorialTask(long i) {
        this.i = i;
    }

    @Override
    protected Long compute() {
        if (i <= 1L) {
            return 1L;
        } else {
            FactorialTask task = new FactorialTask(i - 1);
            task.fork();
            return i * task.join();
        }
    }
}
