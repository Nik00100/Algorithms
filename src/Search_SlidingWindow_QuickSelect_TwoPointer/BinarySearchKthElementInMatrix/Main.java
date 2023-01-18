package Search_SlidingWindow_QuickSelect_TwoPointer.BinarySearchKthElementInMatrix;

/*Given an n x n matrix where each of the rows and columns is sorted in ascending order,
return the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.
You must find a solution with a memory complexity better than O(n2).
Example 1:
Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13*/

import java.util.*;

public class Main {

    /*Algorithm

Start with left = minOfMatrix = matrix[0][0] and right = maxOfMatrix = matrix[n-1][n-1].
Find the mid of the left and the right. This middle number is NOT necessarily an element in the matrix.
If countLessOrEqual(mid) >= k, we keep current ans = mid and try to find smaller value by searching
in the left side. Otherwise, we search in the right side.
Since ans is the smallest value which countLessOrEqual(ans) >= k, so it's the k th smallest element
in the matrix.
How to count number of elements less or equal to x efficiently?

Since our matrix is sorted in ascending order by rows and columns.
We use two pointers, one points to the rightmost column c = n-1, and one points to the lowest row r = 0.
If matrix[r][c] <= x then the number of elements in row r less or equal to x is (c+1) (Because row[r]
is sorted in ascending order, so if matrix[r][c] <= x then matrix[r][c-1] is also <= x).
Then we go to next row to continue counting.
Else if matrix[r][c] > x, we decrease column c until matrix[r][c] <= x (Because column is sorted
in ascending order, so if matrix[r][c] > x then matrix[r+1][c] is also > x).
Time complexity for counting: O(M+N).
It's exactly the same idea with this problem: 240. Search a 2D Matrix II*/

    class BinSearch { // 0 ms, faster than 100%
        int m, n;
        public int kthSmallest(int[][] matrix, int k) {
            m = matrix.length; n = matrix[0].length; // For general, the matrix need not be a square
            int left = matrix[0][0], right = matrix[m-1][n-1], ans = -1;
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (countLessOrEqual(matrix, mid) >= k) {
                    ans = mid;
                    right = mid - 1; // try to looking for a smaller value in the left side
                } else left = mid + 1; // try to looking for a bigger value in the right side
            }
            return ans;
        }
        int countLessOrEqual(int[][] matrix, int x) {
            int cnt = 0, c = n - 1; // start with the rightmost column
            for (int r = 0; r < m; ++r) {
                while (c >= 0 && matrix[r][c] > x) --c;  // decrease column until matrix[r][c] <= x
                cnt += (c + 1);
            }
            return cnt;
        }
    }

    class MinHeap { // 18 ms, faster than 32.44%
        /*Since each of the rows in matrix are already sorted, we can understand the problem as finding
        the kth smallest element from amongst M sorted rows.
        We start the pointers to point to the beginning of each rows, then we iterate k times,
        for each time ith, the top of the minHeap is the ith smallest element in the matrix.
        We pop the top from the minHeap then add the next element which has the same row with
        that top to the minHeap*/

        public int kthSmallest(int[][] matrix, int k) {
            int m = matrix.length, n = matrix[0].length, ans = -1; // For general, the matrix need not be a square
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            for (int r = 0; r < Math.min(m, k); ++r)
                minHeap.offer(new int[]{matrix[r][0], r, 0});

            for (int i = 1; i <= k; ++i) {
                int[] top = minHeap.poll();
                int r = top[1], c = top[2];
                ans = top[0];
                if (c + 1 < n) minHeap.offer(new int[]{matrix[r][c + 1], r, c + 1});
            }
            return ans;
        }
    }
}
