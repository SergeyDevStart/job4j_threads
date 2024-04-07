package ru.sandbox.concurrency.worker;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@ThreadSafe
public class ThreadPoll {
    private final List<Thread> poll = new LinkedList<>();
    private final SimpleBlockingQueue blockingQueue;

    public ThreadPoll(int sizePoll, int sizeTask) {
        blockingQueue = new SimpleBlockingQueue(sizeTask);
        for (int index = 0; index < sizePoll; index++) {
            poll.add(new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        blockingQueue.poll().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
        poll.forEach(Thread::start);
    }

    public void addWork(Runnable task) {
        try {
            blockingQueue.offer(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public void shutdown() {
        poll.forEach(Thread::interrupt);
    }

    public void waitUntilAllTasksFinished() {
        while (!blockingQueue.isEmpty()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static final class SimpleBlockingQueue {
        @GuardedBy("this")
        private final Queue<Runnable> queue = new LinkedList<>();
        private final int size;

        public SimpleBlockingQueue(int size) {
            this.size = size;
        }

        public synchronized void offer(Runnable task) throws InterruptedException {
            while (queue.size() >= size) {
                wait();
            }
            queue.offer(task);
            notifyAll();
        }

        public synchronized Runnable poll() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            Runnable task = queue.poll();
            notifyAll();
            return task;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
}
