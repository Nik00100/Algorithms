package ya_training.algo3_0.task5_01_03.task2;


import java.io.*;
import java.util.*;

public class Main {
    private static List<List<Integer>> graph;
    private static boolean[] visited;


    private static void dfs(int node, List<Integer> connectComponent, int connectComponentNumber) {
        visited[node] = true;
        connectComponent.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, connectComponent, connectComponentNumber);
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

        int connectComponentNumber = 1;
        Map<Integer,List<Integer>> answer = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            List<Integer> connectComponent = answer.getOrDefault(i, new ArrayList<>());
            if (!visited[i]) {
                dfs(i, connectComponent, connectComponentNumber);
                Collections.sort(connectComponent);
                answer.put(connectComponentNumber, connectComponent);
                connectComponentNumber++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < connectComponentNumber; i++) {
            List<Integer> connectComponent = new ArrayList<>(answer.get(i));
            sb.append(connectComponent.size()).append("\n");
            for (int num : connectComponent)
                sb.append(num).append(" ");
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
        }

        System.out.println(answer.size());
        System.out.println(sb.toString().trim());

        reader.close();
    }
}
