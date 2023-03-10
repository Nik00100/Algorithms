package BFS_DFS_UnionFind.BfsMazeII;

/*505. The Maze II

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right,
but it won’t stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball’s start position, the destination and the maze, find the shortest distance for the ball to stop at the destination.
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination
(included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of
the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12

Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
Image text

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1

Explanation: There is no way for the ball to stop at the destination.*/

import java.util.*;

class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        final int m = maze.length;
        final int n = maze[0].length;
        final int[] dirs = {0, 1, 0, -1, 0};
        Queue<int[]> q = new ArrayDeque<>(Arrays.asList(new int[] {start[0], start[1]}));
        int[][] dist = new int[maze.length][maze[0].length];
        Arrays.stream(dist).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        dist[start[0]][start[1]] = 0;

        while (!q.isEmpty()) {
            final int i = q.peek()[0];
            final int j = q.poll()[1];
            for (int k = 0; k < 4; ++k) {
                int x = i;
                int y = j;
                int steps = dist[i][j];
                while (isValid(maze, x + dirs[k], y + dirs[k + 1])) {
                    x += dirs[k];
                    y += dirs[k + 1];
                    ++steps;
                }
                if (steps < dist[x][y]) {
                    dist[x][y] = steps;
                    q.offer(new int[] {x, y});
                }
            }
        }

        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE
                ? -1
                : dist[destination[0]][destination[1]];
    }

    private boolean isValid(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}
