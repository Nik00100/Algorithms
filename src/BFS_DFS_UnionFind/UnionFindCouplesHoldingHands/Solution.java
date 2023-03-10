package BFS_DFS_UnionFind.UnionFindCouplesHoldingHands;

/*765. Couples Holding Hands https://leetcode.com/problems/sliding-puzzle/

There are n couples sitting in 2n seats arranged in a row and want to hold hands.

The people and seats are represented by an integer array row where row[i] is the ID of the person sitting in the ith seat.
The couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last
couple being (2n - 2, 2n - 1).

Return the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people,
then they stand up and switch seats.



Example 1:

Input: row = [0,2,1,3]
Output: 1
Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
Example 2:

Input: row = [3,2,0,1]
Output: 0
Explanation: All couples are already seated side by side.
*
* Think about each couple as a vertex in the graph. So if there are N couples, there are N vertices.
* Now if in position 2i and 2i +1 there are person from couple u and couple v sitting there, that means that
* the permutations are going to involve u and v. So we add an edge to connect u and v. The min number of swaps = N - number
* of connected components. This follows directly from the theory of permutations. Any permutation can be decomposed
* into a composition of cyclic permutations. If the cyclic permutation involve k elements, we need k -1 swaps.
* You can think about each swap as reducing the size of the cyclic permutation by 1. So in the end, if the graph
* has k connected components, we need N - k swaps to reduce it back to N disjoint vertices.

Then there are many ways of doing this. We can use dfs for example to compute the number of connected components.
* The number of edges isn O(N). So this is an O(N) algorithm. We can also use union-find. I think a union-find is
* usually quite efficient. The following is an implementation.*/

class Solution {
    private class UF {
        private int[] parents;
        public int count;
        UF(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            count = n;
        }

        private int find(int i) {
            if (parents[i] == i) {
                return i;
            }
            parents[i] = find(parents[i]);
            return parents[i];
        }

        public void union(int i, int j) {
            int a = find(i);
            int b = find(j);
            if (a != b) {
                parents[a] = b;
                count--;
            }
        }
    }
    public int minSwapsCouples(int[] row) {
        int N = row.length/ 2;
        UF uf = new UF(N);
        for (int i = 0; i < N; i++) {
            int a = row[2*i];
            int b = row[2*i + 1];
            uf.union(a/2, b/2);
        }
        return N - uf.count;
    }
}
