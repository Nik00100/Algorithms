package ya_training.algo3_0.task5_01_03.task1;


import java.io.*;
import java.util.*;

public class Main {
    private static List<List<Integer>> graph;
    private static boolean[] visited;

    private static void dfs(int node, List<Integer> connectComponent) {
        visited[node] = true;
        connectComponent.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, connectComponent);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        graph = new ArrayList<>();
        visited = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] uv = reader.readLine().split(" ");
            int u = Integer.parseInt(uv[0]);
            int v = Integer.parseInt(uv[1]);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        List<Integer> connectComponent = new ArrayList<>();
        dfs(1, connectComponent);
        Collections.sort(connectComponent);
        System.out.println(connectComponent.size());

        StringBuilder sb = new StringBuilder();
        for (int node : connectComponent) {
            sb.append(node).append(" ");
        }

        System.out.println(sb.toString().trim());

        reader.close();
    }
}
