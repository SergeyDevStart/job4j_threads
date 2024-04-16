package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchDemo<T> extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10;
    private final T[] array;
    private final int start;
    private final int end;
    private final T searchElement;

    public ParallelSearchDemo(T[] array, int start, int end, T searchElement) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }

    private Integer lineSearch(int start, int end) {
        for (int index = start; index < end; index++) {
            if (searchElement.equals(array[index])) {
                return index;
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            return lineSearch(start, end);
        }
        int middle = (start + end) / 2;
        ParallelSearchDemo<T> left = new ParallelSearchDemo<>(array, start, middle, searchElement);
        ParallelSearchDemo<T> right = new ParallelSearchDemo<>(array, middle + 1, end, searchElement);
        left.fork();
        right.fork();
        return Math.max(right.join(), left.join());
    }

    public static <T> Integer search(T[] array, T searchElement) {
        return new ForkJoinPool().invoke(new ParallelSearchDemo<>(array, 0, array.length, searchElement));
    }
}
