package DP.DPcombinations;

/*Given an m x n binary matrix filled with 0's and 1's, find the largest square containing
only 1's and return its area.

Solution:
dp(i,j) represents the side length of the maximum square whose bottom right corner
is the cell with index (i,j) in the original matrix.

Example 1:                              -> dp:
Input: char[][] matrix = {              {0,0,0,0,0,0},
        {'1','0','1','0','0'},          {0,1,0,1,0,0},
        {'1','0','1','1','1'},          {0,1,0,1,1,1},
        {'1','1','1','1','1'},          {0,1,1,1,2,2},
        {'1','0','0','1','0'}           {0,1,0,0,1,0},
        };
Output: 4
*/

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        // for convenience, we add an extra all zero column and row
        // outside of the actual dp table, to simpify the transition
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        printMatrix(dp);
        return maxsqlen * maxsqlen;
    }

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(maximalSquare(matrix));
    }
}
