package ru.job4j.threads.threadlocal;

public class ThreadSecond extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.threadLocal.set("Это поток 2.");
        System.out.println(ThreadLocalDemo.threadLocal.get());
    }
}
