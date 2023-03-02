package BFS_DFS_UnionFind.DfsFindSafeStates;

/*802. Find Eventual Safe States https://leetcode.com/problems/find-eventual-safe-states/description/?orderBy=most_votes

There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed
2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from
node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node
leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
Example 1:
  __________________
 /  ________     ___\ ___________
V /    |    \   /    |            \
 /     V     V /     |             V
0      1      2      3      4      5      6
^      |______^_____^ |      \     ^
|_____________________|       \___/

Illustration of graph
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
Example 2:

Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.*/

import java.util.*;

class Solution {
    enum State{
        NotVisited, Safe, UnSafe;
    }

    public List<Integer> eventualSafeNodes(int[][] a) {
        List<Integer> res = new ArrayList<>();
        if(a.length==0) return res;

        State[] state = new State[a.length];
        Arrays.fill(state, State.NotVisited);

        for(int i=0;i<a.length;i++){
            if(!dfs(i, a, state)) res.add(i);
        }

        return res;
    }


    boolean dfs(int cur, int[][] a, State[] state){
        if(state[cur]==State.Safe) return true;
        if(state[cur]==State.UnSafe) return false;

        state[cur]=State.Safe;
        for(int node : a[cur]){
            if(dfs(node, a, state)) return true;
        }
        state[cur]=State.UnSafe;

        return false;
    }
}
