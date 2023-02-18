package ya_training.algo3_0.task2_17_02.task3;

import java.io.*;
import java.util.*;

public class Solution {

    static class Deque<Item>  {
        private Node first;
        private Node last;
        private int numberOfItems;

        private class Node {
            private Item item;
            private Node next;
            private Node prev;

            Node(Item item) {
                this.item = item;
                this.next = null;
                this.prev = null;
            }
        }

        public Deque() {
            this.first = null;
            this.last = null;
            this.numberOfItems = 0;
        }

        public boolean isEmpty() {
            return this.size() == 0;
        }

        public int size() {
            return this.numberOfItems;
        }

        public void offerFirst(Item item) {
            if (item == null) { throw new NullPointerException();   }

            if (this.isEmpty()) {
                this.first = new Node(item);
                this.last = first;
            } else {
                Node node = new Node(item);
                node.next = this.first;
                this.first.prev = node;
                this.first = node;
            }
            this.numberOfItems++;
        }

        public void offerLast(Item item) {
            if (item == null) { throw new NullPointerException();   }

            if (this.isEmpty()) {
                this.last = new Node(item);
                this.first = last;
            } else {
                Node node = new Node(item);
                this.last.next = node;
                node.prev = this.last;
                this.last = node;
            }
            this.numberOfItems++;
        }


        public Item pollFirst() {
            if (this.isEmpty()) { throw new NoSuchElementException(); }

            Node node = this.first;
            if (this.size() == 1) {
                this.first = null;
                this.last = null;
            } else {
                this.first.next.prev = null;
                this.first = this.first.next;
            }
            this.numberOfItems--;
            node.next = null;
            return node.item;
        }

        public Item pollLast() {
            if (this.isEmpty()) { throw new NoSuchElementException(); }

            Node node = this.last;
            if (this.size() == 1) {
                this.first = null;
                this.last = null;
            } else {
                this.last.prev.next = null;
                //this.last.prev = null;
                this.last = this.last.prev;
            }
            this.numberOfItems--;
            node.next = null;
            return node.item;
        }

        public Item getFirst() {
            if (this.isEmpty()) { throw new NoSuchElementException(); }
            Node node = this.first;
            return node.item;
        }


        public Item getLast() {
            if (this.isEmpty()) { throw new NoSuchElementException(); }
            Node node = this.last;
            return node.item;
        }

        public void clear() {
            this.first = null;
            this.last = null;
            this.numberOfItems = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Deque<Integer> deque = new Deque<>();

        while (true) {
            String[] s = reader.readLine().split(" ");
            String command = s[0];

            if (command.equals("exit")) {
                System.out.println("bye");
                break;
            }
            else if (command.equals("push_back")) {
                deque.offerLast(Integer.parseInt(s[1]));
                System.out.println("ok");
            }
            else if (command.equals("push_front")) {
                deque.offerFirst(Integer.parseInt(s[1]));
                System.out.println("ok");
            }
            else if (command.equals("pop_back")) {
                if (!deque.isEmpty()) {
                    int num = deque.pollLast();
                    System.out.println(num);
                } else {
                    System.out.println("error");
                }
            }
            else if (command.equals("pop_front")) {
                if (!deque.isEmpty()) {
                    int num = deque.pollFirst();
                    System.out.println(num);
                } else {
                    System.out.println("error");
                }
            }
            else if (command.equals("front")) {
                if (!deque.isEmpty()) {
                    int num = deque.getFirst();
                    System.out.println(num);
                } else {
                    System.out.println("error");
                }
            }
            else if (command.equals("back")) {
                if (!deque.isEmpty()) {
                    int num = deque.getLast();
                    System.out.println(num);
                } else {
                    System.out.println("error");
                }
            }
            else if (command.equals("size")) {
                System.out.println(deque.size());
            }
            else if (command.equals("clear")) {
                deque.clear();
                System.out.println("ok");
            }
        }

        reader.close();
    }
}

