package ya_training.algo3_0.task1_15_02.divA.task4;

import java.io.*;
import java.util.*;

public class Main {
    /*static long largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        long maxArea = 0;
        int n = heights.length;;
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<n; i++) {
            int currentHeight = heights[i];
            while (!stack.empty() && heights[stack.peek()] >= currentHeight) {
                int height = heights[stack.peek()];
                int wigth = i - stack.pop();
                maxArea = Math.max(maxArea, (long) height *wigth);
            }
            stack.push(i);
        }

        while (!stack.empty()) {
            int height = heights[stack.peek()];
            int wigth = heights.length - stack.pop();
            maxArea = Math.max(maxArea, (long) height *wigth);
        }

        return maxArea;
    }*/
    static long largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;
        for (int i = 0; i <= n; i++){
            int height = (i == n ? 0 : heights[i]);
            if (stack.isEmpty() || height >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                int heightIndex = stack.pop();
                maxArea = Math.max(maxArea, (long) heights[heightIndex] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
                i--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");

        int n = Integer.parseInt(s[0]);

        int[] heights = new int[n];
        for (int i=0; i<n; i++) {
            heights[i] = Integer.parseInt(s[i + 1]);
        }

        System.out.println(largestRectangleArea(heights));

        reader.close();
    }
}
