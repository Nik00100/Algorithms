package Array.ArrayMinInSortedArray;

/*Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,2,4,5,6,7] might become:
[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array
[a[n-1], a[0], a[1], a[2], ..., a[n-2]].
Given the sorted rotated array nums of unique elements, return the minimum element of this array.
You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.*/

public class Main {
    static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length-1;

        if (nums.length==1) return nums[0];
        if (nums.length==2) return nums[0]>nums[1] ? nums[1] : nums[0];
        if (nums[end]>nums[start]) return nums[0];

        while (start<=end) {
            int mid = start + (end-start)/2;
            if (nums[mid] > nums[mid+1]) {
                return nums[mid+1];
            }

            if (nums[mid-1] > nums[mid]) {
                return nums[mid];
            }

            else if (nums[mid] > nums[0]) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int[] nums1 = {11,13,15,17};
        System.out.println(findMin(nums1));
    }
}
