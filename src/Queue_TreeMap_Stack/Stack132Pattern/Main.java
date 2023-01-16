package Queue_TreeMap_Stack.Stack132Pattern;

/*
Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
such that i < j < k and nums[i] < nums[k] < nums[j].

Return true if there is a 132 pattern in nums, otherwise, return false.

Example 1:
Input: nums = [1,2,3,4]
Output: false
Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: nums = [3,1,4,2]
Output: true
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: nums = [-1,3,2,0]
Output: true
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

EXPLANATION:
Here, if we fix the peak, i.e. 3 in the 132 pattern, then we can determine if any numbers on its left and right satisfy
the given pattern. We will do this with the help of a stack. Our stack implementation will take care of the 32 pattern
and then we will iterate over the array to find if any number satisfies the 1 pattern.
See the algorithm below for better understanding.

Algorithm:
    - Create a stack and initialize a variable second with INT_MIN value.
    - Start traversing from the end of array.
    - Check if the current number is lesser than second. If it is, then it means our 132 pattern is satisfied as the stack
        is taking care of the 32 pattern and the current number satisfies the entire pattern. So return true.
    - If the above is not true, update the peak value in the stack. Keep popping from the stack until stack is empty
        OR the top value is greater than the current value.
    - Push the current number into the stack.
    - If the loop terminates, it means that the pattern was not found in the array. So, return false.

Take the sample input as [3, 6, 4, 1, 2] and try out this algorithm using a pen & paper.
You will be able to visualize the method then.

Basically, the top of stack is containing the highest number so far, i.e. 3 and second is containing the second highest
number after the highest number, i.e. 2. So, this satisfies the 32 pattern. Now, we will just keep updating second
and stack top when we encounter a number which is greater than the highest number.

*/

import java.util.Stack;

public class Main {
    public boolean find132pattern (int[] nums) {
        Stack<Integer> stack = new Stack ();
        int second = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums [i] < second)
                return true;
            while (!stack.isEmpty() && nums [i] > stack.peek ())
                second = stack.pop ();
            stack.push (nums [i]);
        }
        return false;
    }
}
