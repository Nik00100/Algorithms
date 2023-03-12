package ya_training.algo3_0.task6_06_03.task3;

/*На клеточном поле, размером NxM (2 ≤ N, M ≤ 250) сидит Q (0 ≤ Q ≤ 10000) блох в различных клетках.
"Прием пищи" блохами возможен только в кормушке - одна из клеток поля, заранее известная.
Блохи перемещаются по полю странным образом, а именно, прыжками, совпадающими с ходом обыкновенного шахматного коня.
Длина пути каждой блохи до кормушки определяется как количество прыжков. Определить минимальное значение суммы
длин путей блох до кормушки или, если собраться блохам у кормушки невозможно, то сообщить об этом. Сбор невозможен,
если хотя бы одна из блох не может попасть к кормушке.

Формат ввода
В первой строке входного файла находится 5 чисел, разделенных пробелом: N, M, S, T, Q. N, M - размеры доски
(отсчет начинается с 1); S, T - координаты клетки - кормушки (номер строки и столбца соответственно),
Q - количество блох на доске. И далее Q строк по два числа - координаты каждой блохи.

Формат вывода
Содержит одно число - минимальное значение суммы длин путей или -1, если сбор невозможен.

Пример 1
Ввод	Вывод
2 2 1 1 1
2 2

-1
Пример 2
Ввод	Вывод
4 4 1 1 16
1 1
1 2
1 3
1 4
2 1
2 2
2 3
2 4
3 1
3 2
3 3
3 4
4 1
4 2
4 3
4 4

42*/

import java.util.*;

public class Main_Fast {
    static int[][] board;
    static int[][] dist;
    static boolean[][] visited;
    static int n, m, s, t, q;
    static int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
    static int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        s = scanner.nextInt() - 1;
        t = scanner.nextInt() - 1;
        q = scanner.nextInt();
        board = new int[n][m];
        dist = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < q; i++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            board[x][y]++;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(s, t));
        visited[s][t] = true;
        dist[s][t] = 0;
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            int x = curr.x;
            int y = curr.y;
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isValid(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new Pair(nx, ny));
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0 && dist[i][j] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                    return;
                }
                sum += board[i][j] * dist[i][j];
            }
        }
        System.out.println(sum);
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
