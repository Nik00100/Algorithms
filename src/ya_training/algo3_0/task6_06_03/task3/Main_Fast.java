package ya_training.algo3_0.task6_06_03.task3;

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
