package ru.job4j.pools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    private static Sums calcSums(int[][] matrix, int index) {
        int sumRow = 0;
        int sumCol = 0;
        for (int k = 0; k < matrix.length; k++) {
            sumRow += matrix[index][k];
            sumCol += matrix[k][index];
        }
        return new Sums(sumRow, sumCol);
    }

    private static CompletableFuture<Sums> asyncTask(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> calcSums(matrix, index));
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = calcSums(matrix, i);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int quantity = matrix.length;
        Sums[] sums = new Sums[quantity];
        Map<Integer, CompletableFuture<Sums>> map = new HashMap<>();
        for (int index = 0; index < (quantity / 2); index++) {
            map.put(index, asyncTask(matrix, index));
            int k = quantity - index - 1;
            map.put(k, asyncTask(matrix, k));
        }
        for (Integer key : map.keySet()) {
            sums[key] = map.get(key).get();
        }
        return sums;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] array = new int[10000][10000];
        int count = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = count++;
            }
        }
        long start = System.currentTimeMillis();
        Sums[] result = sum(array);
        long end = System.currentTimeMillis();
        long startAsync = System.currentTimeMillis();
        Sums[] resultAsync = asyncSum(array);
        long endAsync = System.currentTimeMillis();
        System.out.println((end - start) + " VS " + (endAsync - startAsync));
    }
}
