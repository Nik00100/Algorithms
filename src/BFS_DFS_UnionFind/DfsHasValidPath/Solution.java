package BFS_DFS_UnionFind.DfsHasValidPath;

/*1391. Check if There is a Valid Path in a Grid https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/description/

You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:

1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.

You will initially start at the street of the upper-left cell (0, 0). A valid path in the grid is a path that starts from
the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.

Notice that you are not allowed to change any street.

Return true if there is a valid path in the grid or false otherwise.



Example 1:


Input: grid = [[2,4,3],[6,5,2]]
Output: true
Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
Example 2:


Input: grid = [[1,2,1],[1,2,1]]
Output: false
Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get
stuck at cell (0, 0)
Example 3:

Input: grid = [[1,1,2]]
Output: false
Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).*/

import java.util.*;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return false;
        }

        Map<Integer, int[][]> directions = new HashMap<>();
        directions.put(1, new int[][] {{0, -1}, {0, 1}});
        directions.put(2, new int[][] {{-1, 0}, {1, 0}});
        directions.put(3, new int[][] {{0, -1}, {1, 0}});
        directions.put(4, new int[][] {{0, 1}, {1, 0}});
        directions.put(5, new int[][] {{0, -1}, {-1, 0}});
        directions.put(6, new int[][] {{0, 1}, {-1, 0}});

        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        return dfs(grid, directions, visited, 0, 0, m, n);
    }

    private boolean dfs(int[][] grid, Map<Integer, int[][]> directions, boolean[][] visited, int i, int j, int m, int n) {
        visited[i][j] = true;
        if (i == m - 1 && j == n - 1) {
            return true;
        }

        for (int[] dir : directions.get(grid[i][j])) {
            int ni = i + dir[0], nj = j + dir[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && !visited[ni][nj]) {
                int[][] nextDirs = directions.get(grid[ni][nj]);
                for (int[] nextDir : nextDirs) {
                    if (ni + nextDir[0] == i && nj + nextDir[1] == j) {
                        if (dfs(grid, directions, visited, ni, nj, m, n)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
