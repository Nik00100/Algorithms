package Queue_TreeMap_Stack.TreeMapMeetingRooms;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    /*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
    (si < ei), determine if a person could attend all meetings.
    For example: int[][] intervals = {{0, 30},{5, 10},{15, 20}}; return false.*/
    static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1][1] > intervals[i][0]) return false;
        }
        return true;
    }

    /*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
    (si < ei), find the minimum number of conference rooms required.
     For example: int[][] intervals = {{0, 30}, {5, 10}, {15, 20}}; return 2.*/
    static int minMeetingRooms(int[][] intervals) {
        // use TreeMap for sorting and counting rooms
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }
        int ans = 0;
        int current = 0;
        for(int val : map.values()) {
            current += val;
            ans = Math.max(current,ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {15, 20}, {5, 10}};
        System.out.println(canAttendMeetings(intervals));
        System.out.println(minMeetingRooms(intervals));
    }
}
