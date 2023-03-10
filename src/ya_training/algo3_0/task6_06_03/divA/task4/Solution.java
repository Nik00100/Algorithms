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
import java.util.stream.Collectors;

public class Solution {

    static void bfs(int node, int[][][] dist, List<List<Integer>> adjacent, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        //Arrays.fill(visited, false);
        queue.add(node);
        visited[node] = true;
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            visited[curNode] = false;
            for (int i : adjacent.get(curNode)) {
                if (dist[node][i][0] > dist[node][curNode][1] + 2) {
                    dist[node][i][0] = dist[node][curNode][1] + 2;
                    if (!visited[i]) {
                        visited[i] = true;
                        queue.offer(i);
                    }
                }
                if (dist[node][i][1] > dist[node][curNode][0] + 2) {
                    dist[node][i][1] = dist[node][curNode][0] + 2;
                    if (!visited[i]) {
                        visited[i] = true;
                        queue.offer(i);
                    }
                }
            }
        }
    }

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
        System.out.println("****************");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        // initialize adjacency list
        List<List<Integer>> adjacent = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacent.add(new ArrayList<>());
        }

        // initialize time matrix
        int[][][] dist = new int[n + 1][n + 1][2];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j][0] = 1000;
                dist[i][j][1] = 1000;
            }
        }

        // read edges
        for (int i = 0; i < k; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adjacent.get(u).add(v);
            adjacent.get(v).add(u);
        }

        int m = sc.nextInt();
        // initialize robot locations
        List<Integer> robots = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            int v = sc.nextInt();
            robots.add(v);
            dist[v][v][0] = 0;
            dist[v][v][1] = 0;
        }


        // perform BFS from each robot location
        for (int robot : robots) {
            boolean[] visited = new boolean[n + 1];
            bfs(robot, dist, adjacent, visited);
        }



        // find the minimum time when robots can meet
        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    int curMin = Math.max(dist[i][j][0], dist[i][j][1]);
                    minDist = Math.min(minDist, curMin);
                }
            }
        }

        // output the result
        if (minDist == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minDist);
        }
    }

}

