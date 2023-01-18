package Search_SlidingWindow_QuickSelect_TwoPointer.SlidingWindowMaximum;

/*You are given an array of integers nums, there is a sliding window of size k which is moving from
the very left of the array to the very right. You can only see the k numbers in the window.
Each time the sliding window moves right by one position.

Return the max sliding window.

Example 1:
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:
Input: nums = [1], k = 1
Output: [1]*/

import java.util.*;

public class Main {
     // O(n)
    static int[] maxSlidingWindow_deque(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> q = new ArrayDeque<>(); // Max queue

        for (int i = 0; i < nums.length; ++i) {
            while (!q.isEmpty() && q.peekLast() < nums[i])
                q.pollLast();
            q.offerLast(nums[i]);
            if (i >= k && nums[i - k] == q.peekFirst()) // Out of bound
                q.pollFirst();
            if (i >= k - 1)
                ans[i - k + 1] = q.peekFirst();
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums ={1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.stream(maxSlidingWindow_deque(nums,3)).boxed().toList());
    }
}
