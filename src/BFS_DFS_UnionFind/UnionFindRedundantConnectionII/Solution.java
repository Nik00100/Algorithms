package BFS_DFS_UnionFind.UnionFindRedundantConnectionII;

/*685. Redundant Connection II https://leetcode.com/problems/redundant-connection-ii/description/

In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes
are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one
additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already
existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge
connecting nodes ui and vi, where ui is a parent of child vi.

Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers,
return the answer that occurs last in the given 2D-array.

Example 1:
Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:
Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
Output: [4,1]

1) Check whether there is a node having two parents.
    If so, store them as candidates A and B, and set the second edge invalid.
2) Perform normal union find.
    If the tree is now valid
           simply return candidate B
    else if candidates not existing
           we find a circle, return current edge;
    else
           remove candidate A instead of B.*/

public class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] roots = new int[edges.length+1];
        for (int i = 0; i < roots.length; i++) roots[i] = i;

        int[] candidate1 = null, candidate2 = null;
        for (int[] e : edges){
            int rootx = find(roots, e[0]), rooty = find(roots, e[1]);
            if (rooty != e[1]) candidate1 = e; // _Record the last edge which results in "multiple parents" issue
            else if (rootx == rooty) candidate2 = e; // Record last edge which results in "cycle" issue, if any.
            else roots[rooty] = rootx;
        }

        // If there is only one issue, return this one.
        if (candidate1 == null) return candidate2;
        if (candidate2 == null) return candidate1;

        /* If both issues present, then the answer should be the first edge which results in "multiple parents" issue
        The reason is, when an issue happens, we skip the "union" process.
		Therefore, if both issues happen, it means the incorrent edge which results in "multiple parents" was ignored. */
        for (int[] e : edges) if (e[1] == candidate1[1]) return e;

        return new int[2];
    }

    private int find(int[] roots, int i){
        while (i != roots[i]){
            roots[i] = roots[roots[i]];
            i = roots[i];
        }
        return i;
    }
}
