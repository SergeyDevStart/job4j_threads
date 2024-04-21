package ru.sandbox.concurrency.tasks.stack;

import java.util.concurrent.atomic.AtomicReference;

public class ThreadSafeStack<T> {
    private final AtomicReference<Node<T>> top = new AtomicReference<>();

    public void push(T value) {
        Node<T> newHead = new Node<>(value);
        Node<T> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public T pop() {
        Node<T> newHead;
        Node<T> oldHead;
        do {
            oldHead = top.get();
            if (oldHead == null) {
                return null;
            }
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        oldHead.next = null;
        return oldHead.value;
    }

    private static final class Node<T> {
        Node<T> next;
        final T value;

        public Node(T value) {
            this.value = value;
        }
    }
}
