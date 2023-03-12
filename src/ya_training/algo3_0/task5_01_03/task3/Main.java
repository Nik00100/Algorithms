package ya_training.algo3_0.task5_01_03.task3;

/*Во время контрольной работы профессор Флойд заметил, что некоторые студенты обмениваются записками.
Сначала он хотел поставить им всем двойки, но в тот день профессор был добрым, а потому решил разделить студентов на две группы:
списывающих и дающих списывать, и поставить двойки только первым.

У профессора записаны все пары студентов, обменявшихся записками. Требуется определить, сможет ли он разделить студентов
на две группы так, чтобы любой обмен записками осуществлялся от студента одной группы студенту другой группы.

Формат ввода
В первой строке находятся два числа N и M — количество студентов и количество пар студентов, обменивающихся записками
(1 ≤ N ≤ 102, 0 ≤ M ≤ N(N−1)/2).

Далее в M строках расположены описания пар студентов: два числа, соответствующие номерам студентов, обменивающихся записками
(нумерация студентов идёт с 1). Каждая пара студентов перечислена не более одного раза.

Формат вывода
Необходимо вывести ответ на задачу профессора Флойда. Если возможно разделить студентов на две группы - выведите YES;
иначе выведите NO.

Пример 1
Ввод	Вывод
3 2
1 2
2 3
YES
Пример 2
Ввод	Вывод
3 3
1 2
2 3
1 3
NO*/

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
                possible = false; // значит не двудолен
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
