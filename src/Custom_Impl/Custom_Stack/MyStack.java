package Custom_Impl.Custom_Stack;

public class MyStack<T> {
    private int top;
    private T[] arr;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MyStack(int capacity) {
        this.top = -1;
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public void push(T element) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }
        arr[++top] = element;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T element = arr[top];
        arr[top--] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return arr[top];
    }

    public int size() {
        return top + 1;
    }
}
