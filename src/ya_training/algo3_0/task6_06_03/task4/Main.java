package ya_training.algo3_0.task6_06_03.task4;

import java.io.*;
import java.util.*;

public class Main {
    static final int EMPTY = 0;
    static final int USED = 1;
    static int n;
    static int[][][] cave;
    static Point base;

    static final int[] moveX = {0, 0, 0, 0, -1, 1};
    static final int[] moveY = {0, 0, -1, 1, 0, 0};
    static final int[] moveZ = {-1, 1, 0, 0, 0, 0};

    static class Point {
        int x, y, z;
        Point(int z, int x, int y) {
            this.z = z; this.x = x; this.y = y;
        }
    }


    static boolean correct(int z, int x, int y) {
        if (z < 0 || x < 0 || y < 0 || z == n || x == n || y == n) return false;
        return true;
    }

    static void bfs() {
        int steps = -1;
        Queue<Point> q = new LinkedList<>();
        cave[base.z][base.x][base.y] = 0; // инициализируем количество шагов
        q.add(base);
        while (!q.isEmpty()) {
            Point cur = q.remove();
            if (cur.z == 0) {
                steps = cave[cur.z][cur.x][cur.y];
                break;
            }
            for (int i = 0; i < 6; i++) {
                int x = cur.x + moveX[i];
                int y = cur.y + moveY[i];
                int z = cur.z + moveZ[i];
                if (correct(z, x, y) && cave[z][x][y] == EMPTY) {
                    cave[z][x][y] = cave[cur.z][cur.x][cur.y] + 1;
                    q.add(new Point(z, x, y));
                }
            }
        }
        System.out.println(steps);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        cave = new int[n][n][n];

        for (int z = 0; z < n; z++) {
            reader.readLine();
            for (int x = 0; x < n; x++) {
                String s = reader.readLine();
                for (int y = 0; y < n; y++) {
                    char c = s.charAt(y);
                    if (c == 'S')
                        base = new Point(z, x, y);
                    cave[z][x][y] = (c == '#' ? USED : EMPTY);
                }
            }
        }

        bfs();

        reader.close();
    }
}

