package Queue_TreeMap_Stack.MonotonicStack;

/*Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
Example 1:
         ||
      || ||
      || ||
      || ||    ||
||    || || || ||
|| || || || || ||
2  1  5  6  2  3
Input: int[] heights = {2,1,5,6,2,3};
Output: 10*/

import java.util.Stack;

public class LargestRectangle {
    static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int n = heights.length;;
        Stack<Integer> stack = new Stack<>();
        // fill monotonic stack and calculate maxArea
        for (int i=0; i<n; i++) {
            int currentHeight = heights[i];
            while (!stack.empty() && heights[stack.peek()] >= currentHeight) {
                int height = heights[stack.peek()];
                int wigth = i - stack.pop();
                maxArea = Math.max(maxArea, height*wigth);
            }
            stack.push(i);
        }
        // remove remaining heights from stack (there are height values in stack after traversing)
        while (!stack.empty()) {
            int height = heights[stack.peek()];
            int wigth = (heights.length - 1) - stack.pop();
            maxArea = Math.max(maxArea, height*wigth);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }
}
