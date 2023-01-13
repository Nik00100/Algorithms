package Array_Greedy.ArrayShuffle;

/*Given an integer array nums, design an algorithm to randomly shuffle the array.
All permutations of the array should be equally likely as a result of the shuffling.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the integer array nums.
int[] reset() Resets the array to its original configuration and returns it.
int[] shuffle() Returns a random shuffling of the array.


Example 1:

Input
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
Output
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

Explanation
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
                       // Any permutation of [1,2,3] must be equally likely to be returned.
                       // Example: return [3, 1, 2]
solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]

Algorithm

The Fisher-Yates algorithm is remarkably similar to the brute force solution. On each iteration of the algorithm,
we generate a random integer between the current index and the last index of the array. Then, we swap the elements
at the current index and the chosen index - this simulates drawing (and removing) the element from the hat, as the
next range from which we select a random index will not include the most recently processed one. One small, yet
important detail is that it is possible to swap an element with itself - otherwise, some array permutations would be more
likely than others. */

import java.util.Random;

public class Main {
    class Solution {
        private int[] array;
        private int[] original;

        Random rand = new Random();

        private int randRange(int min, int max) {
            return rand.nextInt(max - min) + min;
        }

        private void swapAt(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        public Solution(int[] nums) {
            array = nums;
            original = nums.clone();
        }

        public int[] reset() {
            array = original;
            original = original.clone();
            return original;
        }

        public int[] shuffle() {
            for (int i = 0; i < array.length; i++) {
                swapAt(i, randRange(i, array.length));
            }
            return array;
        }
    }
}
