package ru.job4j.cas;

public class Stack<T> {
    private Node<T> head;

    public void push(T value) {
        if (head == null) {
            head = new Node<>(value);
        }
        Node<T> tempNode = new Node<>(value);
        tempNode.next = head;
        head = tempNode;
    }

    public T poll() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty.");
        }
        Node<T> tempNode = head;
        head = tempNode.next;
        tempNode.next = null;
        return tempNode.value;
    }

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
