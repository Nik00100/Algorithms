package leetcode4.DfsUnionFindislands;

/*You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected
4-direction (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
The area of an island is the number of cells with a value 1 in the island.
Return the maximum area of an island in grid. If there is no island, return 0.
Example 1:
Input: int[][] grid = {
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}
            };
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:
Input: int[][] grid = {{0,0,0,0,0,0,0,0}};
Output: 0*/

import java.util.Stack;

public class DfsRecursive_Iterative {
    private static int areaOfIslandRecursive(int[][] grid, boolean[][] seen, int r, int c) {
        // array for traverse
        int[] dir = {1, 0, -1, 0, 1};
        // exit condition
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || seen[r][c] || grid[r][c] == 0)
            return 0;
        seen[r][c] = true;
        int area = 0;
        for (int i = 0; i < dir.length - 1; i++) {
            area += areaOfIslandRecursive(grid, seen, r + dir[i], c + dir[i + 1]);
        }
        return area + 1;
    }

    public static int maxAreaOfIslandRecursive(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] seen = new boolean[n][m];
        int maxArea = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                maxArea = Math.max(maxArea,areaOfIslandRecursive(grid,seen,i,j));
            }
        return maxArea;
    }

    public static int maxAreaOfIslandIterative(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] seen = new boolean[n][m];
        int maxArea = 0;
        // array for traverse
        int[] dir = {1, 0, -1, 0, 1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !seen[i][j]) {
                    int area = 0;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[] {i, j});
                    seen[i][j] = true;
                    while (!stack.isEmpty()) {
                        area++;
                        int r = stack.peek()[0];
                        int c = stack.pop()[1];
                        for (int k = 0; k < dir.length - 1; k++) {
                            int newR = r + dir[k];
                            int newC = c + dir[k + 1];
                            //System.out.println(newR + " " + newC);
                            // add to stack condition
                            if (newR >= 0 && newC >= 0 && newR < n && newC < m && !seen[newR][newC]
                                    && grid[newR][newC] == 1) {
                                stack.push(new int[] {newR, newC});
                                seen[newR][newC] = true;
                            }
                        }
                    }
                    maxArea = Math.max(area,maxArea);
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        System.out.println(maxAreaOfIslandRecursive(grid));
        System.out.println(maxAreaOfIslandIterative(grid));
    }
}
