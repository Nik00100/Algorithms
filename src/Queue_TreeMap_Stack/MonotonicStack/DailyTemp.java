package Queue_TreeMap_Stack.MonotonicStack;

/*Given an array of integers temperatures represents the daily temperatures, return an array answer such that
answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
If there is no future day for which this is possible, keep answer[i] == 0 instead.
Example 1:
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:
Input: temperatures = [30,60,90]
Output: [1,1,0]*/

import java.util.Arrays;
import java.util.Stack;

public class DailyTemp {
    static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i>=0; i--) {
            // Popping all indices with a lower or equal temperature than the current index
            while (!stack.empty() && temperatures[stack.peek()] <= temperatures[i])
                stack.pop();
            // If the stack still has elements, then the next warmer temperature exists!
            if (!stack.empty())
                ans[i] = stack.peek() - i;
            // Inserting current index in the stack: monotonicity is maintained!
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.stream(dailyTemperatures(temperatures)).boxed().toList());
    }
}
