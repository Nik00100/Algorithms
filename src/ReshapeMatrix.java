
/*Example 1:
Input: mat = [[1,2],[3,4]], r = 1, c = 4
Output: [[1,2,3,4]]
Example 2:
Input: mat = [[1,2],[3,4]], r = 2, c = 4
Output: [[1,2],[3,4]]*/

import java.util.Stack;

public class ReshapeMatrix {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length, total = m * n;
        if(r * c != total) return mat;
        int[][] ans = new int[r][c];
        for(int i = 0; i < total; i++)
            ans[i / c][i % c] = mat[i / n][i % n];
        return ans;
    }
}
