package leetcode2.DPdungeon;

/*The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned
in the top-left room and must fight his way through dungeon to rescue the princess.
The knight has an initial health point represented by a positive integer. If at any point his health point
drops to 0 or below, he dies immediately.
Some of the rooms are guarded by demons (represented by negative integers), so the knight loses health upon
entering these rooms; other rooms are either empty (represented as 0) or contain magic orbs that increase
the knight's health (represented by positive integers).
To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
Return the knight's minimum initial health so that he can rescue the princess.
Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right
room where the princess is imprisoned.

Example 1:
Input:
    int[][] dungeon = {
        {-2,-3,3},
        {-5,-10,1},
        {10,30,-5}
    };
Output: 7
Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
Example 2:
Input: dungeon = [[0]]
Output: 1*/

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        //dp[i][j] represents the minimum health points needed at position (i,j) and
        //the last row or column is just dummy (outside of the range).
        int[][] dp = new int[m+1][n+1];

        //Initialize the matrix to maximum possible.
        for (int i = 0; i <= m; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        //initializing the boundary.
        dp[m][n-1] = 1; dp[m-1][n] = 1;

        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int minHp = Math.min(dp[i+1][j], dp[i][j+1])  - dungeon[i][j];
                dp[i][j] = (minHp <= 0) ? 1 : minHp;
            }
        }
        printMatrix(dp);
        return dp[0][0];
    }

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
    }

    public static void main(String[] args) {
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println(calculateMinimumHP(dungeon));
    }
}
