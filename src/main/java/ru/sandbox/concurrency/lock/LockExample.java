package ru.sandbox.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ATM atm = new ATM(lock);
        Thread user1 = new Thread(atm, "user1");
        Thread user2 = new Thread(atm, "user2");
        Thread user3 = new Thread(atm, "user3");
        user1.start();
        user2.start();
        user3.start();
    }
}

class ATM implements Runnable {
    private final Lock lock;

    public ATM(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " ожидает...");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " использует банкомат.");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " закончил работу.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
