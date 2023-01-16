package Queue_TreeMap_Stack.StackNextGreaterElement;

/*503. Next Greater Element II https://leetcode.com/problems/next-greater-element-ii/description/

Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
return the next greater number for every element in nums.
The next greater number of a number x is the first greater number to its traversing-order next in the array,
which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
Example 1:
Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number.
The second 1's next greater number needs to search circularly, which is also 2.
Example 2:
Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
*
* This approach makes use of a stack. This stack stores the indices of the appropriate elements from nums array.
* The top of the stack refers to the index of the Next Greater Element found so far. We store the indices instead
* of the elements since there could be duplicates in the nums array. The description of the method will
* make the above statement clearer.
* We start traversing the nums array from right towards the left. For an element nums[i]nums[i]nums[i] encountered,
* we pop all the elements stack[top] from the stack such that nums[stack[top]]≤nums[i].
* We continue the popping till we encounter a stack[top] satisfying nums[stack[top]]>nums[i].
* Now, it is obvious that the current stack[top] only can act as the Next Greater Element for nums[i]
* (right now, considering only the elements lying to the right of nums[i]). If no element remains on the top of the stack,
* it means no larger element than nums[i] exists to its right. Along with this, we also push the index of the element
* just encountered(nums[i]), i.e. i over the top of the stack, so that nums[i] (or stack[top]) now acts as the
* Next Greater Element for the elements lying to its left.
* We go through two such passes over the complete nums array. This is done so as to complete a circular traversal
* over the nums array. The first pass could make some wrong entries in the res array since it considers only the elements
* lying to the right of nums[i], without a circular traversal. But, these entries are corrected in the second pass.

Further, to ensure the correctness of the method, let's look at the following cases.

Assume that nums[j] is the correct Next Greater Element for nums[i], such that i<j≤stack[top].
* Now, whenever we encounter nums[j], if nums[j]>nums[stack[top]], it would have already popped the
* previous stack[top] and j would have become the topmost element. On the other hand, if nums[j]<nums[stack[top]]
* it would have become the topmost element by being pushed above the previous stack[top]
* In both the cases, if nums[j]>nums[i], it will be correctly determined to be the Next Greater Element.*/

import java.util.Stack;

public class NextGreaterElementII {
    public static int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }
}
