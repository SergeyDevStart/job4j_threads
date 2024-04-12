package ru.job4j.sorted;

public class MergeSortExample {
    public static int[] sort(int[] arr) {
        return sort(arr, 0, arr.length - 1);
    }

    private static int[] sort(int[] arr, int from, int to) {
        if (from == to) {
            return new int[] {arr[from]};
        }
        int middle = (from + to) / 2;
        return merge(sort(arr, from, middle), sort(arr, middle + 1, to));
    }

    public static int[] merge(int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] result = new int[left.length + right.length];
        while (i < left.length && j < right.length) {
            result[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }
}
