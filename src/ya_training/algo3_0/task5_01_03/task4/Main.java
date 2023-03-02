package ya_training.algo3_0.task5_01_03.task4;

import java.io.*;
import java.util.*;

public class Main {
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static Deque<Integer> stack;
    static int[] colors;

    static void dfs(int node) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
        stack.push(node);
    }

    static boolean dfsForCycle(int node) {
        colors[node] = 1;
        for (int neighbor : graph.get(node)) {
            if (colors[neighbor] == 0) {
                if (dfsForCycle(neighbor))
                    return true;
            } else if (colors[neighbor] == 1) {
                return true;
            }
        }
        colors[node] = 2;
        return false;
    }

    static boolean hasCycle() {
        for (int i = 0; i < graph.size(); i++) {
            if (dfsForCycle(i)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        visited = new boolean[n];
        colors = new int[n];
        stack = new ArrayDeque<>();
        graph = new ArrayList<>(n);

        Arrays.fill(colors, 0);

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] uv = reader.readLine().split(" ");
            int u = Integer.parseInt(uv[0]) - 1;
            int v = Integer.parseInt(uv[1]) - 1;
            graph.get(u).add(v);
        }

        boolean error = hasCycle();
        if (error) {
            System.out.println("-1");
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop() + 1).append(" ");
            }
            System.out.println(sb.toString().trim());
        }

        reader.close();

    }
}
