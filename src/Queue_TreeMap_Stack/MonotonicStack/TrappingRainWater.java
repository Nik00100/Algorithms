package Queue_TreeMap_Stack.MonotonicStack;

/*Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.
Example 1:
    ^   ^ ^ ^   X ^
      X       X X   X
  X   X X   X X X X X X
- - - - - - - - - - - -
0,1,0,2,1,0,1,3,2,1,2,1
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

The main idea is : if we want to find out how much water on a bar (pre index),
we need to find out the left larger bar's index (indexLeft), and right larger bar's index(indexRight),
so that the water is (min(A[indexLeft],A[indexRight])-A[pre])*(indexRight-indexLeft-1),
use min since only the lower boundary can hold water, and we also need to handle the edge case that there is no indexLeft.*/

import java.util.Stack;

public class TrappingRainWater {
    static int trap(int[] height) {
        if (height == null) return 0;
        int n = height.length;
        int volume = 0;
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        while (i < n) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i++);
            } else {
                int pre = stack.pop();
                if (!stack.isEmpty()) {
                    // find the smaller height between the two sides
                    int minHeight = Math.min(height[stack.peek()], height[i]);
                    // calculate the area
                    volume += (minHeight - height[pre]) * (i - stack.peek() - 1);
                }
            }
        }

        return volume;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }
}
