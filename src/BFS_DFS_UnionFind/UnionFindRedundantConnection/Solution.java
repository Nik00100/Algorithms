package BFS_DFS_UnionFind.UnionFindRedundantConnection;

/*684. Redundant Connection

In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is
represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in
the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers,
return the answer that occurs last in the input.
Example 1:
Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:
Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]

The parent array keeps track of the parent of each vertex in the graph, while the rank array keeps track of the rank of each
vertex in the graph. The find method recursively finds the root of a vertex's tree and updates the parent array for that vertex
and its descendants along the way, using path compression to optimize the search. The union method performs a union of the trees
containing the two given vertices x and y, updating the parent and rank arrays accordingly.

The findRedundantConnection method iterates through each edge in the input array and calls the union method to determine if the
edge forms a cycle in the graph. If a cycle is detected, the method returns the redundant edge. If no cycle is detected, the method
throws an IllegalArgumentException.*/

import java.util.*;

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        int[] rank = new int[edges.length + 1];
        Arrays.fill(parent, -1);

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (!union(x, y, parent, rank)) {
                return edge;
            }
        }

        throw new IllegalArgumentException("Illegal input.");
    }

    private int find(int x, int[] parent) {
        if (parent[x] == -1) {
            return x;
        }
        parent[x] = find(parent[x], parent);
        return parent[x];
    }

    private boolean union(int x, int y, int[] parent, int[] rank) {
        int root_x = find(x, parent);
        int root_y = find(y, parent);

        if (root_x == root_y) {
            return false;
        }

        if (rank[root_x] < rank[root_y]) {
            parent[root_x] = root_y;
            rank[root_y] += rank[root_x];
        } else {
            parent[root_y] = root_x;
            rank[root_x] += rank[root_y];
        }

        return true;
    }

}
