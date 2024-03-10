package ru.job4j.threads.threadlocal;

public class ThreadFirst extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.threadLocal.set("Это поток 1.");
        System.out.println(ThreadLocalDemo.threadLocal.get());
    }
}
