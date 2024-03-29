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

import java.util.*;
import java.io.*;

public class Solution {
    static int n, k, nr;
    static int[][][] m = new int[401][401][2];
    static int[][] l = new int[401][401];
    static int[] r = new int[401];
    static int[] a = new int[20001];
    static int[] b = new int[20001];

    static boolean[] u = new boolean[401];
    static int[] o = new int[1000001];

    static boolean[][] s = new boolean[401][401];

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        n = sc.nextInt();
        for (int i = 1; i <= 400; i++) {
            for (int j = 1; j <= 400; j++) {
                m[i][j][0] = m[i][j][1] = 1000_000;
            }
        }
        k = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            m[i][i][0] = 0;
        }
        for (int i = 1; i <= k; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
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

        nr = sc.nextInt();
        for (int i = 1; i <= nr; i++) {
            r[i] = sc.nextInt();
        }

        for (int i = 1; i <= nr; i++) {
            bfs(r[i]);
        }

        int an = 1000000;
        for (int i = 1; i <= k; i++) {
            int c = 0;
            for (int j = 1; j <= nr; j++) {
                c = Math.max(c, Math.min(Math.min(m[r[j]][a[i]][0], m[r[j]][a[i]][1]),
                        Math.min(m[r[j]][b[i]][0], m[r[j]][b[i]][1])) + 1);
            }
            an = Math.min(an, c);
        }
        for (int i = 1; i <= n; i++) {
            int lc = 0;
            int ln = 0;
            for (int j = 1; j <= nr; j++) {
                lc = Math.max(lc, m[r[j]][i][0]);
                ln = Math.max(ln, m[r[j]][i][1]);
            }
            an = Math.min(an, Math.min(lc, ln));
        }

        if (an < 100000) {
            //out.printf("%.1f\n", an / 2.0);
            double minTimeForAllRobots = (double) an / 2.0;
            double reminder = minTimeForAllRobots % 1;
            if (reminder == 0.0) {
                System.out.println((int) minTimeForAllRobots);
            } else {
                System.out.println(minTimeForAllRobots);
            }
        } else {
            out.println("-1");
        }

        sc.close();
        out.close();
    }
    static void bfs(int v) {
        Arrays.fill(u, false);
        int p1 = 1;
        int p2 = 1;
        o[p1] = v;
        u[v] = true;
        while (p1 <= p2) {
            int cv = o[p1++];
            u[cv] = false;
            for (int i = 1; i <= l[cv][0]; i++) {
                if (m[v][l[cv][i]][0] > m[v][cv][1] + 2) {
                    m[v][l[cv][i]][0] = m[v][cv][1] + 2;
                    if (!u[l[cv][i]]) {
                        u[l[cv][i]] = true;
                        o[++p2] = l[cv][i];
                    }
                }
                if (m[v][l[cv][i]][1] > m[v][cv][0] + 2) {
                    m[v][l[cv][i]][1] = m[v][cv][0] + 2;
                    if (!u[l[cv][i]]) {
                        u[l[cv][i]] = true;
                        o[++p2] = l[cv][i];
                    }
                }
            }
        }
    }

}