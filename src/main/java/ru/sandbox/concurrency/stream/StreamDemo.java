package ru.sandbox.concurrency.stream;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> list = IntStream.range(1, 4 + 1).boxed().toList();
        list.parallelStream().forEach(i -> {
            System.out.println("Start Thread: " + Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Finish Thread: " + Thread.currentThread().getName() + " : " + i);
        });
    }
}
