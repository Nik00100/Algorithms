package BFS_DFS_UnionFind.BfsMaze;

/*490. The Maze (Medium)
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right,
but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders
of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1:
Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.*/

import java.util.*;

class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        final int m = maze.length;
        final int n = maze[0].length;
        final int[] dirs = {0, 1, 0, -1, 0};
        Queue<int[]> q = new ArrayDeque<>(Arrays.asList(new int[] {start[0], start[1]}));
        boolean[][] seen = new boolean[m][n];
        seen[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            final int i = q.peek()[0];
            final int j = q.poll()[1];
            for (int k = 0; k < 4; ++k) {
                int x = i;
                int y = j;
                while (isValid(maze, x + dirs[k], y + dirs[k + 1])) {
                    x += dirs[k];
                    y += dirs[k + 1];
                }
                if (x == destination[0] && y == destination[1])
                    return true;
                if (seen[x][y])
                    continue;
                q.offer(new int[] {x, y});
                seen[x][y] = true;
            }
        }

        return false;
    }

    private boolean isValid(int[][] maze, int x, int y) {
        return 0 <= x && x < maze.length && 0 <= y && y < maze[0].length && maze[x][y] == 0;
    }
}

