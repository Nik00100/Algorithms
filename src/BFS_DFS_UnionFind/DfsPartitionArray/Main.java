package BFS_DFS_UnionFind.DfsPartitionArray;

/*Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.



Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.*/

import java.util.Arrays;

public class Main {
    class Solution {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for(int i : nums){
                sum += i;
            }
            if(sum % 2 == 1) return false;
            int target = sum /2;

            Arrays.sort(nums);
            Boolean[][] memo = new Boolean[nums.length + 1][target + 1];
            return dfs(nums, target, 0, memo);
        }

        public boolean dfs(int[] nums,
                           int target,
                           int index,
                           Boolean[][] memo){


            if(target == 0) return true;
            if(target < 0) return false;
            if(index > nums.length) return false;

            if(memo[index][target] != null) return memo[index][target];

            for(int i = index; i < nums.length; i++){
                if(dfs(nums, target - nums[i], i + 1, memo))
                    return memo[index][target - nums[i]] = true;
            }

            return memo[index][target] = false;
        }
    }
}
