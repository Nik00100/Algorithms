package QueueTreemapStack.TreeMapMeetingRooms;

import java.util.*;

class PriorityQueueVariant {
    static int minMeetingRooms(int[][] intervals) {
        // Store end times of each room
        Queue<Integer> minHeap = new PriorityQueue<>();

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && interval[0] >= minHeap.peek())
                minHeap.poll(); // No overlap, we can reuse the same room
            minHeap.offer(interval[1]);
        }

        return minHeap.size();
    }

    public static void main(String[] args) {
        int[][] interv = {{1, 2}, {1, 3}, {2, 4}, {2, 3}};
        System.out.println(minMeetingRooms(interv));
    }
}