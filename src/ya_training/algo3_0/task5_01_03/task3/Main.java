package ya_training.algo3_0.task5_01_03.task3;

import java.io.*;
import java.util.*;

public class Main {
    private static List<List<Integer>> graph;
    private static int[] colors;
    static boolean possible = true;

    static void dfs(int node, int color) {
        colors[node] = color;
        for (int neighbor : graph.get(node)) {
            if (colors[neighbor] == 0) { // если вершина не покрашена
                dfs(neighbor, 3 - color); // идем в другую вершину
            }
            else if (colors[neighbor] == color) { // ребро соединяет вершины одинакового цвета,граф не двудолен
                possible = false; // не двудолен - значит не двудолен
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        colors = new int[n];
        Arrays.fill(colors, 0);

        graph = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] uv = reader.readLine().split(" ");
            int u = Integer.parseInt(uv[0]) - 1;
            int v = Integer.parseInt(uv[1]) - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) { // если мы еще не посетили вершину
                dfs(i, 1); // идем ее красить
            }
        }

        if (possible) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        reader.close();

    }
}
