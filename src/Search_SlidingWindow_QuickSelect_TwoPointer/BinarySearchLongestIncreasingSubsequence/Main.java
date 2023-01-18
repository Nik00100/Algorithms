package Search_SlidingWindow_QuickSelect_TwoPointer.BinarySearchLongestIncreasingSubsequence;

/*Given an integer array nums, return the length of the longest strictly increasing subsequence
Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Let's see example nums = [2, 6, 8, 3, 4, 5, 1]:
    Let pick the first element, sub = [2].
    6 is greater than previous number, sub = [2, 6]
    8 is greater than previous number, sub = [2, 6, 8]
    3 is less than previous number, so we can't extend the subsequence sub.
        We need to find the smallest number >= 3 in sub, it's 6. Then we overwrite it, now sub = [2, 3, 8].
    4 is less than previous number, so we can't extend the subsequence sub. We overwrite 8 by 4, so sub = [2, 3, 4].
    5 is greater than previous number, sub = [2, 3, 4, 5].
    1 is less than previous number, so we can't extend the subsequence sub. We overwrite 2 by 1, so sub = [1, 3, 4, 5].
Finally, length of longest increase subsequence = len(sub) = 4.

(1) if x is larger than all subs, append it
(2) if subs[i-1] < x <= subs[i], update subs[i]

                binary search another variant
     int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int size = 0;

        for(int num: nums) {
            // binary search
            int left = 0, right = size, middle = 0;     // right = size
            while(left < right) {
                middle = left + (right - left) / 2;
                if(dp[middle] < num) left = middle + 1;
                else right = middle;
            }

            // left is the right position to 'replace' in dp array
            dp[left] = num;
            if(left == size) size++;
        }
        return size;
     }*/


import java.util.*;

public class Main {
    static int lengthOfLIS(int[] nums) {
        List<Integer> subs = new ArrayList<>();
        for (int x : nums) {
            int index = Collections.binarySearch(subs,x);
            if (index < 0) {
                index = ~index;
            }
            if (index == subs.size()) {
                subs.add(x);
            } else {
                subs.set(index,x);
            }
        }
        return subs.size();
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
