package ya_training.algo3_0.task5_01_03.task1;

/*Дан неориентированный граф, возможно, с петлями и кратными ребрами. Необходимо построить компоненту связности,
содержащую первую вершину.

Формат ввода
В первой строке записаны два целых числа N (1 ≤ N ≤ 103) и M (0 ≤ M ≤ 5 * 105) — количество вершин и ребер в графе.
В последующих M строках перечислены ребра — пары чисел, определяющие номера вершин, которые соединяют ребра.

Формат вывода
В первую строку выходного файла выведите число K — количество вершин в компоненте связности. Во вторую строку выведите
K целых чисел — вершины компоненты связности, перечисленные в порядке возрастания номеров.

Пример
Ввод	Вывод
4 5
2 2
3 4
2 3
1 3
2 4

4
1 2 3 4*/

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
