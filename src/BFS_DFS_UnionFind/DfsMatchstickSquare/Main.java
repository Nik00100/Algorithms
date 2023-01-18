package BFS_DFS_UnionFind.DfsMatchstickSquare;

/*473. Matchsticks to Square https://leetcode.com/problems/matchsticks-to-square/

You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up,
and each matchstick must be used exactly one time. Return true if you can make this square and false otherwise.
Example 1:
Input: matchsticks = [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: matchsticks = [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.

Depth First Search
It is possible that a matchstick can be a part of any of the 4 sides of the resulting square, but which one of these choices
leads to an actual square is something we don't know.
This means that for every matchstick in our given array, we have 4 different options each representing the side of the
square or subset that this matchstick can be a part of.
We try out all of them and keep on doing this recursively until we exhaust all of the possibilities or until we find an
arrangement of our matchsticks such that they form the square.

Algorithm
    - As discussed previously, we will follow a recursive, depth first approach to solve this problem.
        So, we have a function that takes the current matchstick index we are to process and also the number of sides
        of the square that are completely formed till now.

    - If all of the matchsticks have been used up and 4 sides have been completely formed, that implies our square
        is completely formed. This is the base case for the recursion.

    - For the current matchstick we have 4 different options. This matchstick at index can be a part of any of the sides
        of the square. We try out the 4 options by recursing on them.

    - If any of these recursive calls returns True, then we return from there, else we return False

This solution is very slow as is. However, we can speed it up considerably by a small trick and that is to sort our
matchsticks sizes in reverse order before processing them recursively.

The reason for this is that if there is no solution, trying a longer matchstick first will get to negative conclusion earlier.*/

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class Solution {
        public List<Integer> nums;
        public int[] sums;
        public int possibleSquareSide;

        public Solution() {
            this.sums = new int[4];
        }

        // Depth First Search function.
        public boolean dfs(int index) {

            // If we have exhausted all our matchsticks, check if all sides of the square are of equal length
            if (index == this.nums.size()) {
                return sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3];
            }

            // Get current matchstick.
            int element = nums.get(index);

            // Try adding it to each of the 4 sides (if possible)
            for(int i = 0; i < 4; i++) {
                if (sums[i] + element <= possibleSquareSide) {
                    sums[i] += element;
                    if (dfs(index + 1)) {
                        return true;
                    }
                    sums[i] -= element;
                }
            }

            return false;
        }

        public boolean makesquare(int[] nums) {
            // Empty matchsticks.
            if (nums == null || nums.length == 0) {
                return false;
            }

            // Find the perimeter of the square (if at all possible)
            int L = nums.length;
            int perimeter = 0;
            for(int i = 0; i < L; i++) {
                perimeter += nums[i];
            }

            possibleSquareSide =  perimeter / 4;
            if (possibleSquareSide * 4 != perimeter) {
                return false;
            }

            // Convert the array of primitive int to ArrayList (for sorting).
            this.nums = Arrays.stream(nums).boxed().collect(Collectors.toList());
            Collections.sort(this.nums, Collections.reverseOrder());
            return this.dfs(0);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2,2};
        System.out.println(new Solution().makesquare(nums));
    }
}
