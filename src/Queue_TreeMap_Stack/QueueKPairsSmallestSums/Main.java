package Queue_TreeMap_Stack.QueueKPairsSmallestSums;

/*You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
Define a pair (u, v) which consists of one element from the first array and one element from second array.
Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
Example 1:
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]*/

import java.util.*;

public class Main {
    static class T {
        public int i;
        public int j;
        public int sum; // nums1[i] + nums2[j]
        public T(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }
    }

    static class Solution {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<List<Integer>> ans = new ArrayList<>();
            Queue<T> minHeap = new PriorityQueue<>((a, b) -> a.sum - b.sum);

            for (int i = 0; i < k && i < nums1.length; ++i)
                minHeap.offer(new T(i, 0, nums1[i] + nums2[0]));

            while (!minHeap.isEmpty() && ans.size() < k) {
                final int i = minHeap.peek().i;
                final int j = minHeap.poll().j;
                ans.add(Arrays.asList(nums1[i], nums2[j]));
                if (j + 1 < nums2.length)
                    minHeap.offer(new T(i, j + 1, nums1[i] + nums2[j + 1]));
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,7,11}, nums2 = {2,4,6};
        int k = 3;
        System.out.println(new Solution().kSmallestPairs(nums1,nums2,k));
    }
}
