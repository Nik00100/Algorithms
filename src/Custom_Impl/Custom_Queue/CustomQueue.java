package Custom_Impl.Custom_Queue;

import java.util.NoSuchElementException;

public class CustomQueue<T> {
    private int size;
    private int front;
    private int rear;
    private Object[] elements;

    public CustomQueue(int capacity) {
        size = 0;
        front = 0;
        rear = -1;
        elements = new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full.");
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = item;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        T item = (T) elements[front];
        front = (front + 1) % elements.length;
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return (T) elements[front];
    }
}
