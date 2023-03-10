package ya_training.algo3_0.task5_01_03.divA.task2;

/*Дан ориентированный граф, возможно, с петлями и кратными ребрами.

Необходимо найти все вершины, из которых достижима первая вершина.

Формат ввода
В первой строке записаны два целых числа N (1 ≤ N ≤ 103) и M (0 ≤ M ≤ 5 * 105) — количество вершин и ребер в графе.

В последующих M строках перечислены ребра — пары чисел, определяющие номера вершин, которые соединяют ребра
(в порядке «откуда» и «куда» ведет ребро).

Формат вывода
Выведите все вершины, из которых достижима первая, в порядке возрастания их номеров.

Пример
Ввод	Вывод
4 5
2 2
4 3
2 3
3 1
2 4
1 2 3 4 */

import java.io.*;
import java.util.*;

public class Main {
    private final static int FIND_NODE = 1;
    private static List<List<Integer>> graph;

    private static boolean dfs(int node, boolean[] visited) {
        if (node == FIND_NODE) return true;
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (neighbor == FIND_NODE || dfs(neighbor, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = reader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(FIND_NODE).add(FIND_NODE);

        for (int i = 0; i < m; i++) {
            String[] uv = reader.readLine().split(" ");
            int u = Integer.parseInt(uv[0]);
            int v = Integer.parseInt(uv[1]);
            graph.get(u).add(v);
        }

        List<Integer> reachable = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            boolean flag = dfs(i, visited);
            if (flag) {
                reachable.add(i);
            }
        }

        Collections.sort(reachable);
        StringBuilder sb = new StringBuilder();
        for (int i : reachable) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString().trim());

        reader.close();
    }
}
