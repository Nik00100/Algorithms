package leetcode.ArrayIntersections;

import java.util.*;

public class Main {
    static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> numbers = new HashSet<>();
        for (int n : nums1) { numbers.add(n); }
        int[] res = new int[numbers.size()];
        int cursor = 0;
        for (int n : nums2) {
            if (numbers.remove(n)) {
                res[cursor++] = n;
            }
        }
        return Arrays.copyOfRange(res,0,cursor);
    }

    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        Arrays.stream(intersection(nums1,nums2)).forEach(i-> System.out.print(i+" "));
    }
}
