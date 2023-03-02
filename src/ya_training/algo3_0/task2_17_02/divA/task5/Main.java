package ya_training.algo3_0.task2_17_02.divA.task5;

import java.util.*;

public class Main {

    static int n, k, p;
    static int len = 0;
    static int[] heap = new int[100000];
    static int[] idx = new int[100000];
    static List<Integer>[] prior = new ArrayList[100000];
    static int[] seq = new int[500000];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        k = input.nextInt();
        p = input.nextInt();
        for (int i = 0; i < prior.length; i++) {
            prior[i] = new ArrayList<Integer>();
        }
        Arrays.fill(idx, -1);
        for (int i = 0; i < p; i++) {
            seq[i] = input.nextInt() - 1;
            prior[seq[i]].add(i);
        }
        int answer = 0;
        for (int i = 0; i < p; i++) {
            prior[seq[i]].remove(0);
            if (idx[seq[i]] != -1) {
                siftup(idx[seq[i]]);
                continue;
            }
            if (len < k) {
                add(seq[i]);
            } else {
                remove();
                add(seq[i]);
            }
            answer++;
        }
        System.out.println(answer);
    }

    static int parent(int i) {
        return (i - 1) >> 1;
    }

    static int left(int i) {
        return (i << 1) + 1;
    }

    static int right(int i) {
        return (i << 1) + 2;
    }

    static void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        idx[heap[i]] = i;
        idx[heap[j]] = j;
    }

    static boolean greater(int i, int j) {
        if (!prior[heap[i]].isEmpty() && !prior[heap[j]].isEmpty()) {
            return prior[heap[i]].get(0) > prior[heap[j]].get(0);
        } else {
            return prior[heap[i]].isEmpty() && !prior[heap[j]].isEmpty();
        }
    }

    static void siftup(int i) {
        while (i > 0 && greater(i, parent(i))) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    static void siftdown(int i) {
        while (true) {
            int j = i;
            if (left(j) < len && greater(left(j), i)) {
                i = left(j);
            }
            if (right(j) < len && greater(right(j), i)) {
                i = right(j);
            }
            if (i != j) {
                swap(i, j);
            } else {
                break;
            }
        }
    }

    static void add(int item) {
        idx[item] = len;
        heap[len] = item;
        siftup(len++);
    }

    static void remove() {
        swap(0, --len);
        idx[heap[len]] = -1;
        siftdown(0);
    }

}
