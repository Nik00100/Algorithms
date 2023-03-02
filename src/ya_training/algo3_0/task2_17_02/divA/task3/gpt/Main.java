package ya_training.algo3_0.task2_17_02.divA.task3.gpt;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        Vector<Integer> buff = new Vector<>();

        int p = 0;
        int n = 0;
        int count = 0;

        Scanner input = new Scanner(System.in);
        n = input.nextInt();

        for (int i = 0; i < n; ++i) {
            int arrival_time = input.nextInt();
            int departure_time = input.nextInt();
            if (heap.isNotEmpty()) {
                while (heap.isNotEmpty() && arrival_time <= heap.topHeap()) {
                    buff.add(heap.extractMax());
                    ++count;
                }
                if (heap.isNotEmpty() && arrival_time > heap.topHeap()) {
                    heap.extractMax();
                    for (int j = buff.size() - 1; j >= 0; --j) {
                        heap.push(buff.get(j));
                        buff.remove(j);
                    }
                }
                if (!heap.isNotEmpty()) {
                    for (int j = buff.size() - 1; j >= 0; --j) {
                        heap.push(buff.get(j));
                        buff.remove(j);
                    }
                }
            }

            heap.push(departure_time);
        }

        System.out.println(heap.getCount());
    }

    static class Heap<T extends Comparable<T>> {
        private ArrayList<T> buffer;
        private int count;

        public Heap() {
            this.buffer = new ArrayList<T>();
            this.count = 0;
            buildHeap();
        }

        private void siftDown(int index) {
            int i = 0;
            while (i * 2 + 2 < buffer.size()) {
                if (buffer.get(i * 2 + 1).compareTo(buffer.get(i * 2 + 2)) > 0) {
                    if (buffer.get(i * 2 + 1).compareTo(buffer.get(i)) > 0) {
                        Collections.swap(buffer, i * 2 + 1, i);
                        i = i * 2 + 1;
                    }
                    else {
                        break;
                    }
                }
                else {
                    if (buffer.get(i * 2 + 2).compareTo(buffer.get(i)) > 0) {
                        Collections.swap(buffer, i * 2 + 2, i);
                        i = i * 2 + 2;
                    }
                    else {
                        break;
                    }
                }
            }
            if (i * 2 + 1 < buffer.size()) {
                if (buffer.get(i * 2 + 1).compareTo(buffer.get(i)) > 0) {
                    Collections.swap(buffer, i * 2 + 1, i);
                }
            }
        }

        private void siftUp(int index) {
            int parent = 0;
            while (index > 0) {
                parent = (index - 1) / 2;
                if (buffer.get(index).compareTo(buffer.get(parent)) > 0) {
                    Collections.swap(buffer, index, parent);
                }
                else {
                    break;
                }
                index = parent;
            }
        }

        public void push(T value) {
            buffer.add(value);
            siftUp(count);
            ++count;
        }

        public T extractMax() {
            assert !buffer.isEmpty();
            T max = buffer.get(0);
            buffer.set(0, buffer.get(count - 1));
            buffer.remove(count - 1);
            siftDown(0);
            --count;
            return max;
        }

        private void buildHeap() {
            for (int i = buffer.size() / 2 - 1; i >= 0; --i) {
                siftDown(i);
            }
        }

        public int getCount() {
            return count;
        }

        public T topHeap() {
            return buffer.get(0);
        }

        public boolean isNotEmpty() {
            return count > 0;
        }
    }
}

