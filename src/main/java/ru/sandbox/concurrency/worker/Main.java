package ru.sandbox.concurrency.worker;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoll threadPoll = new ThreadPoll(3, 10);
        for (int i = 0; i < 10; i++) {
            threadPoll.addWork(new Task(i));
        }
        threadPoll.waitUntilAllTasksFinished();
        threadPoll.shutdown();
    }
}
