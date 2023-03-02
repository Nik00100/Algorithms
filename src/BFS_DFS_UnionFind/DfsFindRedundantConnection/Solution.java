package BFS_DFS_UnionFind.DfsFindRedundantConnection;

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

alreadyConnected variable is a Map that represents the graph where the keys represent the vertices and the values represent
the adjacent vertices. The isAlreadyConnected method takes two vertices x and y and a Set visited to keep track of visited
vertices during the DFS traversal. It returns true if x is already connected to y and false otherwise.
The findRedundantConnection method iterates through all the edges, and for each edge, it checks if the vertices are already
connected using the isAlreadyConnected method. If the vertices are already connected, it returns the edge as the answer.
If no redundant edge is found, it returns a 0-length array as the answer.
*/

import java.util.*;

class Solution {
    Map<Integer, List<Integer>> alreadyConnected;

    public int[] findRedundantConnection(int[][] edges) {
        alreadyConnected = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (isAlreadyConnected(x, y, new HashSet<Integer>())) {
                return edge;
            }
            alreadyConnected.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            alreadyConnected.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }
        return new int[2];
    }

    private boolean isAlreadyConnected(int x, int y, Set<Integer> visited) {
        if (x == y) {
            return true;
        }
        visited.add(x);
        if (!alreadyConnected.containsKey(x)) {
            return false;
        }
        for (int xAdjacent : alreadyConnected.get(x)) {
            if (!visited.contains(xAdjacent) && isAlreadyConnected(xAdjacent, y, visited)) {
                return true;
            }
        }
        return false;
    }
}

