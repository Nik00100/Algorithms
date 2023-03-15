package ya_training.algo3_0.final_sprint.task5.test;

import java.util.*;

class Main {
    static final int INF = 1_000_000_000;

    static final int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    static final int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};

    static void turn(int[][] field, int x, int y, Queue<int[]> q) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            while (field[nx][ny] == -1 || field[nx][ny] == field[x][y] + 1) {
                if (field[nx][ny] == -1) {
                    q.offer(new int[] {nx, ny});
                    field[nx][ny] = field[x][y] + 1;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt(), w = sc.nextInt();
        int[][] field = new int[h + 2][w + 2];
        for (int i = 1; i <= h; i++) {
            String row = sc.next();
            for (int j = 1; j <= w; j++) {
                field[i][j] = row.charAt(j - 1) == '.' ? -1 : -2;
            }
        }

        int sx = sc.nextInt(), sy = sc.nextInt(), tx = sc.nextInt(), ty = sc.nextInt();
        int[][] dist = new int[h + 2][w + 2];
        for (int i = 0; i < h + 2; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[h - sy + 1][sx] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {h - sy + 1, sx});

        while (!q.isEmpty()) {
            int[] elem = q.poll();
            turn(dist, elem[0], elem[1], q);
        }

        System.out.println(dist[h - ty + 1][tx]);
    }
}
