package leetcode.ArrayMinInSortedArray;

/*Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,4,4,5,6,7] might become:
[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
You must decrease the overall operation steps as much as possible.

Example 1:
Input: nums = [1,3,5]
Output: 1
Example 2:
Input: nums = [2,2,2,0,1]
Output: 0*/

public class Hard {
    static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length-1;

        if (nums.length==1) return nums[0];
        if (nums[end]>nums[start]) return nums[0];

        while (start<=end) {
            if (nums[start] == nums[end]) {
                end--;
            } else if (nums[start] < nums[end]) {
                return nums[start];
            } else {
                start++;
            }
        }
        return nums[start];
    }

    public static void main(String[] args) {
        int[] nums = {2,2,2,0,1};
        System.out.println(findMin(nums));
    }
}
