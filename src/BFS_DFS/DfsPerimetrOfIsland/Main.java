package BFS_DFS.DfsPerimetrOfIsland;

/*You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there
is exactly one island (i.e., one or more connected land cells).
The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
Determine the perimeter of the island.
Example 1:
Input: int[][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
                };
Output: 16*/

public class Main {
    static int[] DIR = new int[]{0, 1, 0, -1, 0};
    public static int islandPerimeter(int[][] grid) {
        int row = grid.length, col = grid[0].length, perimeter = 0;
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (grid[r][c] == 0) continue; // Skip water cell
                perimeter += 4;
                for (int i = 0; i < 4; ++i) {
                    int nr = r + DIR[i], nc = c + DIR[i+1];
                    if (nr < 0 || nr == row || nc < 0 || nc == col || grid[nr][nc] == 0) continue;
                    perimeter -= 1;
                }
            }
        }
        return perimeter;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };
        System.out.println(islandPerimeter(grid));
    }
}
