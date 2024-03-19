package ru.job4j.threads.condition;

public class RiceConditionExample {
    private int num = 1;

    public synchronized void increment() {
        for (int index = 0; index < 99999; index++) {
            int current = num;
            int next = ++num;
            if (current + 1 != next) {
                throw new IllegalStateException("Некорректное сравнение: " + current + " + 1 = " + next);
            }
        }
    }
}

class Main {
    public static void main(String[] args) throws InterruptedException {
        RiceConditionExample riceConditionExample = new RiceConditionExample();
        Thread one = new Thread(riceConditionExample::increment);
        one.start();
        Thread two = new Thread(riceConditionExample::increment);
        two.start();
        one.join();
        two.join();
    }
}
