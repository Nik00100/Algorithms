package BFS_DFS_UnionFind.BacktrackNonDecreasSubseq;

/*491. Non-decreasing Subsequences

Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least
two elements. You may return the answer in any order.

Example 1:

Input: nums = [4,6,7,7]
Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]*/

import java.util.*;

public class Main {

    static class Solution {
        public List<List<Integer>> findSubsequences(int[] nums) {
            Set<List<Integer>> ans = new HashSet<>();
            List<Integer> seq = new ArrayList<Integer>();
            backtrack(nums,0,seq,ans);
            return new ArrayList<>(ans);
        }

        public void backtrack(int[] nums, int index, List<Integer> subseq, Set<List<Integer>> ans) {
            // if we have checked all elements
            if (index == nums.length) {
                if (subseq.size() >=2) ans.add(new ArrayList<>(subseq));
                return;
            }
            // if the sequence remains increasing after appending nums[index], append nums[index] to the sequence
            if (subseq.isEmpty() || subseq.get(subseq.size()-1) <= nums[index]) {
                subseq.add(nums[index]);
                // call recursion for index+1 element
                backtrack(nums,index+1,subseq,ans);
                // delete nums[index] from the end of the sequence
                subseq.remove(subseq.size()-1);
            }
            // call recursively not appending an element
            backtrack(nums,index+1,subseq,ans);
        }
    }


    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
        System.out.println(new Solution().findSubsequences(nums));
    }
}
