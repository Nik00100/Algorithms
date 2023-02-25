package Custom_Impl.Custom_Deque;

import java.util.NoSuchElementException;

class Deque<T> {
    private T[] array;
    private int size;
    private int front;
    private int rear;

    public Deque(int capacity) {
        array = (T[]) new Object[capacity];
        size = 0;
        front = -1;
        rear = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }
        if (front == -1) {
            front = rear = 0;
        } else if (front == 0) {
            front = array.length - 1;
        } else {
            front--;
        }
        array[front] = item;
        size++;
    }

    public void addLast(T item) {
        if (isFull()) {
            throw new IllegalStateException("Deque is full");
        }
        if (front == -1) {
            front = rear = 0;
        } else if (rear == array.length - 1) {
            rear = 0;
        } else {
            rear++;
        }
        array[rear] = item;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        T item = array[front];
        if (front == rear) {
            front = rear = -1;
        } else if (front == array.length - 1) {
            front = 0;
        } else {
            front++;
        }
        size--;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        T item = array[rear];
        if (front == rear) {
            front = rear = -1;
        } else if (rear == 0) {
            rear = array.length - 1;
        } else {
            rear--;
        }
        size--;
        return item;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return array[front];
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return array[rear];
    }
}
