package KMP_BoyerMoore.BoyerMooreMajorityElement;

/*Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3
Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2*/

import java.util.HashMap;
import java.util.Map;

public class UsingHashMap {
    static int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        int n = nums.length/2;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i =0; i<nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int value = map.get(nums[i]);
                value++;
                if (value>n) return nums[i];
                map.put(nums[i],value);
            } else {
                map.put(nums[i],1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums ={2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }
}
