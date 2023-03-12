package ya_training.algo3_0.task5_01_03.task5;

/*Дан неориентированный граф. Требуется определить, есть ли в нем цикл, и, если есть, вывести его.

Формат ввода
В первой строке дано одно число n — количество вершин в графе ( 1≤ n ≤500 ). Далее в n строках задан сам граф матрицей смежности.

Формат вывода
Если в иcходном графе нет цикла, то выведите «NO». Иначе, в первой строке выведите «YES», во второй строке выведите число
k — количество вершин в цикле, а в третьей строке выведите k различных чисел — номера вершин, которые принадлежат циклу
в порядке обхода (обход можно начинать с любой вершины цикла). Если циклов несколько, то выведите любой.

Пример 1
Ввод	Вывод
3
0 1 1
1 0 1
1 1 0

YES
3
3 2 1
Пример 2
Ввод	Вывод
4
0 0 1 0
0 0 0 1
1 0 0 0
0 1 0 0

NO
Пример 3
Ввод	Вывод
5
0 1 0 0 0
1 0 0 0 0
0 0 0 1 1
0 0 1 0 1
0 0 1 1 0

YES
3
5 4 3*/


import java.io.*;
import java.util.*;

public class Main {
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    static int[] parent;
    static List<Integer> cycle;

    private static boolean dfs(int node, int p) {
        visited[node] = true;
        parent[node] = p;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, node)) {
                    return true;
                }
            } else if (neighbor != p) {
                cycle = new ArrayList<>();
                cycle.add(neighbor);
                for (int i = node; i != neighbor; i = parent[i]) {
                    cycle.add(i);
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        int n = Integer.parseInt(reader.readLine().trim());

        parent = new int[n];
        graph = new ArrayList<>();
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String[] row = reader.readLine().trim().split(" ");
            graph.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (row[j].equals("1")) {
                    graph.get(i).add(j);
                }
            }
        }

        boolean hasCycle = false;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && dfs(i, -1)) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(cycle.size());
            StringBuilder sb = new StringBuilder();
            for (int i = cycle.size() - 1; i >= 0; i--) {
                sb.append(cycle.get(i) + 1).append(" ");
            }
            System.out.println(sb.toString().trim());
        }

        reader.close();
    }

}
