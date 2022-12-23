package leetcode.maxGapBucketAndDirichlet;

/*Given an integer array nums, return the maximum difference between two successive elements in its sorted form.
If the array contains less than two elements, return 0.
You must write an algorithm that runs in linear time and uses linear extra space.

Example 1:
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
Example 2:
Input: nums = [10]
Output: 0


Explanation:
Suppose there are N elements in the array, the min value is min and the max value is max.
Then the maximum gap will be no smaller than Math.ceil((max - min ) / (N - 1)).
Let gap = Math.ceil((max - min ) / (N - 1)). We allocate all numbers in the Bucket array,
length = 1 + ((max-min)/gap) - this is Dirichlet's Principle.
We only need to store the largest number and the smallest number in each bucket.
We can calculate bucketIndex = (num[i] - min)/gap
We can calculate gapBetweenToBuckets = buckets[i+1].min - buckets[i].max
After we put all the numbers into the buckets, we can scan the buckets sequentially and get the max gap.
*/

import java.util.Arrays;

public class BucketSort {
    static class Bucket {
        int min;
        int max;
        public Bucket(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    static int maximumGap(int[] nums) {
        if (nums.length<2) return 0;

        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        if (max == min) return 0;

        // calculate minGap and bucket array length and initialize bucket array
        int gap = (int) Math.ceil((double) (max-min)/(nums.length-1));
        int length = (max-min)/gap+1;
        Bucket[] buckets = new Bucket[length];
        for (int i=0; i<length; i++) {
            buckets[i] = new Bucket(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // fill buckets
        for (int num : nums) {
            int bucketIndex = (num - min)/ gap;
            buckets[bucketIndex].min = Math.min(num,buckets[bucketIndex].min);
            buckets[bucketIndex].max = Math.max(num,buckets[bucketIndex].max);
        }

        // calculate maxGap
        int ans = 0;
        int prevBucketMax = min;
        for (Bucket bucket : buckets) {
            if (bucket.min == Integer.MAX_VALUE) continue; // empty bucket
            ans = Math.max(ans,bucket.min - prevBucketMax);
            prevBucketMax = bucket.max;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {49,37,25,43,29,21,3,9};
        System.out.println(maximumGap(nums));
    }
}
