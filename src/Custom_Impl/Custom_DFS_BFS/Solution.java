package Custom_Impl.Custom_DFS_BFS;

import java.util.*;

public class Solution {

    public static boolean hasPathDFS(int[][] graph, int source, int destination) {
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];

        stack.push(source);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (current == destination) {
                return true;
            }

            if (visited[current]) {
                continue;
            }

            visited[current] = true;

            for (int neighbor : graph[current]) {
                stack.push(neighbor);
            }
        }

        return false;
    }

    public int[] findRedundantConnectionBFS(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int[] parent = new int[edges.length + 1];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph.getOrDefault(u, Collections.emptyList())) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    queue.offer(v);
                } else if (v != parent[u]) {
                    return new int[] {Math.min(u, v), Math.max(u, v)};
                }
            }
        }

        return new int[2];
    }


}


