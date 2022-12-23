package leetcode.findPeek;

public class LinearScan {
    static int findPeakElement(int[] nums) {
        int right = nums.length - 1;
        int i=0;
        while (i<right) {
            if (nums[i]>nums[i+1]) {
                return i;
            }
            i++;
        }
        return nums.length-1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 8, 9, 4};
        System.out.println(findPeakElement(nums));
    }
}
