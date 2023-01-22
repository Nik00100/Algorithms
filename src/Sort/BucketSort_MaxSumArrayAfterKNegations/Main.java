package Sort.BucketSort_MaxSumArrayAfterKNegations;

/*1005. Maximize Sum Of Array After K Negations

Given an integer array nums and an integer k, modify the array in the following way:
choose an index i and replace nums[i] with -nums[i]. You should apply this process exactly k times.
You may choose the same index i multiple times. Return the largest possible sum of the array after modifying it in this way.
Example 1:
Input: nums = [4,2,3], k = 1
Output: 5
Explanation: Choose index 1 and nums becomes [4,-2,3].
Example 2:
Input: nums = [3,-1,0,2], k = 3
Output: 6
Explanation: Choose indices (1, 2, 2) and nums becomes [3,1,0,2].
1 <= nums.length <= 104
-100 <= nums[i] <= 100
1 <= k <= 104
*
*
* Since the A[i] is limited to [-100, 100], we can use an fixed-size array cnt to count occurrences.
* Bucket sort (or, more precise, countint sort), in other words :)

Then, as we process numbers [-100, 100], we flip the negative numbers by moving count from cnt[i + 100] to cnt[-i + 100].
This guaranties that, if K > 0 after processing all negative numbers, the first positive number will have the smallest
absolute value.

Therefore, when we encounter the first positive number, and our K % 2 == 1, we negate one occurrence of that number.*/

public class Main {
    int largestSumAfterKNegations(int[] A, int K) {
        int[] cnt = new int[201];
        int res = 0;
        for (int i : A) ++cnt[i + 100];
        for (int i = -100; i <= 100 && K > 0; ++i) {
            if (cnt[i + 100] > 0) {
                int k = i < 0 ? Math.min(K, cnt[i + 100]) : K % 2;
                cnt[-i + 100] += k;
                cnt[i + 100] -= k;
                K = i < 0 ? K - k : 0;
            }
        }
        for (int i = -100; i <= 100; ++i) res += i * cnt[i + 100];
        return res;
    }
}
