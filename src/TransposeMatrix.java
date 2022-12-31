/*You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees
(clockwise). You have to rotate the image in-place, which means you have to modify
the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
       **  -->  ***          1 iteration
    ^  |1|  2  |3|  |
    |   4   5   6   |
    |  |7|  8  |9|  v
      temp  <-- *
Example 1:
Input: int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9}};
Output:    {{7,4,1},
            {8,5,2},
            {9,6,3}}
*/

import java.util.Arrays;
import java.util.stream.Collectors;

public class TransposeMatrix {
    public static void rotate(int[][] matrix) { // если матрица квадратная
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    static int[][] transpose(int[][] A) { // если матрица прямоугольная
        int R = A.length, C = A[0].length;
        int[][] ans = new int[C][R];
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                ans[c][r] = A[r][c];
            }
        return ans;
    }

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}};
        rotate(matrix);
        printMatrix(matrix);
    }
}
