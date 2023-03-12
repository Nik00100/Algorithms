package ya_training.algo3_0.task6_06_03.divA.task4;

/*В подземелье есть N залов, соединенных туннелями. В некоторых залах находятся роботы, которые одновременно получили команду
собраться в одном месте.

Роботы устроены так, что, получив команду, они все начали двигаться с такой скоростью, что туннель между двумя любыми залами
преодолевают за 1 минуту. Роботы не могут останавливаться (в том числе и в залах), а также менять направление движения,
находясь в туннелях (однако попав в зал, робот может из него пойти по тому же туннелю, по которому он пришел в этот зал).

Напишите программу, вычисляющую, через какое минимальное время все роботы смогут собраться вместе (в зале или в туннеле).

Формат ввода
Сначала на вход программы поступают числа N — количество залов (1≤N≤400) и K — количество туннелей (1≤K≤20000). Далее вводится
K пар чисел, каждая пара описывает номера залов, соединяемых туннелем (по туннелю можно перемещаться в обе стороны).
Между двумя залами может быть несколько туннелей. Туннель может соединять зал с самим собой. Далее следует число
M (1≤M≤400) — количество роботов. Затем вводятся M чисел, задающих номера залов, где вначале расположены роботы.
В одном зале может быть несколько роботов.

Формат вывода
Выведите минимальное время в минутах, через которое роботы могут собраться вместе. Если роботы никогда не смогут собраться вместе,
выведите одно число –1 (минус один).

Пример 1
Ввод	Вывод
4 5
1 2
2 3
3 4
1 4
1 3
3
1 2 4

1
Пример 2
Ввод	Вывод
3 2
1 2
2 3
2
1 3

1
Пример 3
Ввод	Вывод
7 7
1 2
2 3
3 4
4 1
1 3
2 6
6 7
3
7 2 4

2*/

import java.io.*;
import java.util.*;

public class Main {
    static int n, k, m;
    static int[][][] dist = new int[401][401][2];
    static int[] robots = new int[401];
    static boolean[] visited = new boolean[401];
    static int[][] l = new int[401][401];
    static int[] a = new int[20001];
    static int[] b = new int[20001];
    static boolean[][] s = new boolean[401][401];
    static final int INF = 1000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String[] nk = reader.readLine().split(" ");

        n = Integer.parseInt(nk[0]);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j][0] = dist[i][j][1] = INF;
            }
        }
        k = Integer.parseInt(nk[1]);
        for (int i = 1; i <= n; i++) {
            dist[i][i][0] = 0;
        }
        for (int i = 1; i <= k; i++) {
            String[] ab = reader.readLine().split(" ");
            a[i] = Integer.parseInt(ab[0]);
            b[i] = Integer.parseInt(ab[1]);
            s[a[i]][b[i]] = true;
            s[b[i]][a[i]] = true;
        }

        for (int i = 1; i <= n; i++) {
            l[i][0] = 0;
            for (int j = 1; j <= n; j++) {
                if (s[i][j]) {
                    ++l[i][0];
                    l[i][l[i][0]] = j;
                }
            }
        }

        m = Integer.parseInt(reader.readLine());
        String[] r = reader.readLine().split(" ");
        for (int i = 1; i <= m; i++) {
            robots[i] = Integer.parseInt(r[i - 1]);
        }

        for (int i = 1; i <= m; i++) {
            bfs(robots[i]);
        }

        int an = INF;
        for (int i = 1; i <= k; i++) {
            int c = 0;
            for (int j = 1; j <= m; j++) {
                c = Math.max(c, Math.min(Math.min(dist[robots[j]][a[i]][0], dist[robots[j]][a[i]][1]),
                        Math.min(dist[robots[j]][b[i]][0], dist[robots[j]][b[i]][1])) + 1);
            }
            an = Math.min(an, c);
        }
        for (int i = 1; i <= n; i++) {
            int lc = 0;
            int ln = 0;
            for (int j = 1; j <= m; j++) {
                lc = Math.max(lc, dist[robots[j]][i][0]);
                ln = Math.max(ln, dist[robots[j]][i][1]);
            }
            an = Math.min(an, Math.min(lc, ln));
        }

        if (an < INF) {
            double minTimeForAllRobots = (double) an / 2.0;
            double reminder = minTimeForAllRobots % 1;
            if (reminder == 0.0) {
                System.out.println((int) minTimeForAllRobots);
            } else {
                System.out.println(minTimeForAllRobots);
            }
        } else {
            System.out.println("-1");
        }

        reader.close();
    }

    static void bfs(int v) {
        Arrays.fill(visited, false);
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visited[v] = true;
        while (!q.isEmpty()) {
            int cv = q.poll();
            visited[cv] = false;
            for (int i = 1; i <= l[cv][0]; i++) {
                if (dist[v][l[cv][i]][0] > dist[v][cv][1] + 2) {
                    dist[v][l[cv][i]][0] = dist[v][cv][1] + 2;
                    if (!visited[l[cv][i]]) {
                        visited[l[cv][i]] = true;
                        q.add(l[cv][i]);
                    }
                }
                if (dist[v][l[cv][i]][1] > dist[v][cv][0] + 2) {
                    dist[v][l[cv][i]][1] = dist[v][cv][0] + 2;
                    if (!visited[l[cv][i]]) {
                        visited[l[cv][i]] = true;
                        q.add(l[cv][i]);
                    }
                }
            }
        }
    }
}
