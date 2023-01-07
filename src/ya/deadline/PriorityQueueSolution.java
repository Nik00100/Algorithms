package ya.deadline;

import java.util.*;

public class PriorityQueueSolution {
    static long getDay(PriorityQueue<Long> heap, long x, long k) {
        long res = 0;
        while (!heap.isEmpty() && k>0) {
            long next = heap.poll();
            res = next;
            while (!heap.isEmpty() && next == heap.peek()) {
                heap.remove();
            }
            heap.offer(next+x);
            k--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] deadlines = {1, 2, 3, 4, 5, 6};
        int[] deadlines1 = {5, 8, 13, 17, 22};
        PriorityQueue<Long> heap = new PriorityQueue<>();
        for (int num: deadlines1) {
            heap.add(Long.valueOf(num));
        }
        long x = 7;
        long k = 12;
        System.out.println(getDay(heap,x,k));
    }
}
