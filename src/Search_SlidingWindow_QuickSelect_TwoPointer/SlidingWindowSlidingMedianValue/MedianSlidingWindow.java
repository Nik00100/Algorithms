package Search_SlidingWindow_QuickSelect_TwoPointer.SlidingWindowSlidingMedianValue;

/*480. Sliding Window Median https://leetcode.com/problems/sliding-window-median/description/

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
So the median is the mean of the two middle values.

For examples, if arr = [2,3,4], the median is 3.
For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
You are given an integer array nums and an integer k. There is a sliding window of size k which is moving
from the very left of the array to the very right. You can only see the k numbers in the window.
Each time the sliding window moves right by one position.

Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
Explanation:
Window position                Median
---------------                -----
[1  3  -1] -3  5  3  6  7        1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7        3
 1  3  -1  -3 [5  3  6] 7        5
 1  3  -1  -3  5 [3  6  7]       6

Inspired by this solution. to the problem: 295.Find Median from Data Stream (Queue_TreeMap_Stack -> FindMedianFromDataStream)

However instead of using two priority queue's we use two Tree Sets as we want O(logk) for remove(element).
Priority Queue would have been O(k) for remove(element) giving us an overall time complexity of O(nk) instead of O(nlogk).
However there is an issue when it comes to duplicate values so to get around this we store the index into nums in our Tree Set.
To break ties in our Tree Set comparator we compare the index.*/

import java.util.*;

public class MedianSlidingWindow {
    static class Solution {
        public double[] medianSlidingWindow(int[] nums, int k) {
            Comparator<Integer> comparator = (a,b) ->  nums[a]==nums[b] ? a -b : Integer.compare(nums[a], nums[b]);
            TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
            TreeSet<Integer> right = new TreeSet<>(comparator);
            double[] res = new double[nums.length - k + 1];

            for (int i = 0; i < k; i++) {
                left.add(i);
            }
            balance(left, right);
            res[0] = getMedian(k, nums, left, right);

            int r = 1;
            for (int i = k; i < nums.length; i++) {
                if(!left.remove(i - k)) {
                    right.remove(i - k);
                }
                right.add(i);
                left.add(right.pollFirst());
                balance(left, right);
                res[r] = getMedian(k, nums, left, right);
                r++;
            }

            return res;
        }

        private void balance(TreeSet<Integer> left, TreeSet<Integer> right) {
            while (left.size() > right.size()) {
                right.add(left.pollFirst());
            }
        }

        private double getMedian(int k, int[] nums, TreeSet<Integer> left, TreeSet<Integer> right) {
            if (k % 2 == 0) {
                return ((double) nums[left.first()] + nums[right.first()]) / 2;
            }
            else {
                return (double) nums[right.first()];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.stream(new Solution().medianSlidingWindow(nums,k)).boxed().toList());
    }
}
