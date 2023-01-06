package leetcode.ArrayFindPeekElement;

public class Iterative {
    static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left<right) {
            mid = (left+right)/2;
            if (nums[mid]>nums[mid+1]) {
                right=mid;
            } else {
                left=mid+1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 8, 9, 4};
        System.out.println(findPeakElement(nums));
    }
}
