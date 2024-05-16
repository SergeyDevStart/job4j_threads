package ru.sandbox.concurrency.lock;

public class ConditionExampleDemo {
    public static void main(String[] args) throws InterruptedException {
        ConditionBaseDemo conditionBaseDemo = new ConditionBaseDemo();
        Thread thread1 = new Thread(conditionBaseDemo::first);
        Thread thread2 = new Thread(conditionBaseDemo::second);
        Thread thread3 = new Thread(conditionBaseDemo::third);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
    }
}
