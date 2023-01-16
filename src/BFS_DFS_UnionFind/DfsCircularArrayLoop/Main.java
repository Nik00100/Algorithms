package BFS_DFS_UnionFind.DfsCircularArrayLoop;

/*457. Circular Array Loop https://leetcode.com/problems/circular-array-loop/description/

You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of
indices forward/backward you must move if you are located at index i:

If nums[i] is positive, move nums[i] steps forward, and
If nums[i] is negative, move nums[i] steps backward.
Since the array is circular, you may assume that moving forward from the last element puts you on the first element,
and moving backwards from the first element puts you on the last element.

A cycle in the array consists of a sequence of indices seq of length k where:

Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
Every nums[seq[j]] is either all positive or all negative.
k > 1
Return true if there is a cycle in nums, or false otherwise.

Example 1:
            __________
           |          |
            0 -> 2 -> 3
            ^
            |
            1 <- 4
Input: nums = [2,-1,1,2,2]
Output: true
Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
We can see the cycle 0 --> 2 --> 3 --> 0 --> ..., and all of its nodes are white (jumping in the same direction).*/

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static boolean circularArrayLoop(int[] nums) {

        HashMap<Integer, Boolean> map = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            if(cycleExists(i, map, nums)) {
                return true;
            }
        }
        return false;
    }

    private static boolean cycleExists(int index, HashMap<Integer, Boolean> map, int[] nums) {
        if(map.containsKey(index)) {
            return map.get(index);
        }

        map.put(index, true); // mark as true to indicate currently visiting
        int next = Math.floorMod((index + nums[index]), nums.length);

        if(next != index /* ensure cycle's length > 1 */ && nums[next] * nums[index] > 0 /* ensure single direction */) {
            if(cycleExists(next, map, nums)) {
                return true;
            }
        }

        map.put(index, false); // mark as false to indicate done visiting, no cycle found
        return false;
    }


    public static void main(String[] args) {
        int[] nums = {2,-1,1,2,2};
        System.out.println(circularArrayLoop(nums));
    }
}
