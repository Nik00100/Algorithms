package CustomImpl.customStack;

public class MinStack {
    private Node head;
    public MinStack() {
    }

    public void push(int value) {
        if (head == null) {
            head = new Node(value,value,null);
        } else {
            head = new Node(value,Math.min(value,head.min),head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int value;
        int min;
        Node next;

        public Node(int value, int min, Node next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }
}
