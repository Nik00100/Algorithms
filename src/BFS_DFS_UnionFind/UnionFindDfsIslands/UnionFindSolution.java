package BFS_DFS_UnionFind.UnionFindDfsIslands;

/*A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand
operation which turns the water at position (row, col) into a land. Given a list of positions to operate,
count the number of islands after each addLand operation. An island is surrounded by water and is formed
by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all
surrounded by water.

Example:
Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
1 1 0
0 0 1   Number of islands = 3
0 1 0*/

import java.util.*;

public class UnionFindSolution {
    static class UnionFind {
        // массив id хранит для каждой вершины дерева её непосредственного предка (а для корня дерева X — его самого).
        public int[] id;
        // массив rank хранит верхнюю границу его высоты дерева — то есть длиннейшей ветви в нем
        private int[] rank;
        public UnionFind(int n) {
            id = new int[n];
            rank = new int[n];
            Arrays.fill(id, -1); // вода
        }

        // подвешиваем более низкое дерево к более высокому, и если их высоты равны — не играет роли, кого подвешивать к кому.
        // но новоиспеченному корню надо увеличить rank.
        public void unionByRank(int u, int v) {
            final int i = find(u);
            final int j = find(v);
            if (i == j) return;
            if (rank[i] < rank[j]) {
                id[i] = id[j];
            } else if (rank[i] > rank[j]) {
                id[j] = id[i];
            } else {
                id[i] = id[j];
                ++rank[j];
            }
        }

        // сжатие путей (path compression)
        // для каждой вершины по пути от X к корню изменим предка на этого самого представителя
        public int find(int u) {
            return id[u] == u ? u : (id[u] = find(id[u]));
        }


    }

    static class Islands {
        final int[] dirs = {0, 1, 0, -1, 0};

        // DFS
        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            boolean[][] seen = new boolean[m][n];
            List<Integer> ans = new ArrayList<>();
            UnionFind uf = new UnionFind(m * n);
            int count = 0;

            for (int[] p : positions) {
                final int i = p[0];
                final int j = p[1];
                if (seen[i][j]) {
                    ans.add(count);
                    continue;
                }
                seen[i][j] = true;
                final int id = getId(i, j, n);
                uf.id[id] = id;
                ++count;
                for (int k = 0; k < 4; ++k) {
                    final int x = i + dirs[k];
                    final int y = j + dirs[k + 1];
                    if (x < 0 || x == m || y < 0 || y == n)
                        continue;
                    final int neighborId = getId(x, y, n);
                    if (uf.id[neighborId] == -1) // вода
                        continue;
                    final int currentParent = uf.find(id);
                    final int neighborParent = uf.find(neighborId);
                    if (currentParent != neighborParent) {
                        uf.unionByRank(currentParent, neighborParent);
                        --count;
                    }
                }
                ans.add(count);
            }

            return ans;
        }

        private int getId(int i, int j, int n) {
            return i * n + j;
        }
    }

    public static void main(String[] args) {
        int m = 3, n = 3;
        int[][] positions = {{0,0}, {0,1}, {1,2}, {2,1}};
        System.out.println(new Islands().numIslands2(m,n,positions));
    }
}
