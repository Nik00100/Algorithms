package ya_training.algo3_0.task5_01_03.divA.task4;

import java.util.*;
import java.io.*;

public class Solution {
    private final static int N_MAX = 101;
    static int[][] map = new int[N_MAX][N_MAX];
    static int[][] colors = new int[N_MAX][N_MAX];
    static int[] s = new int[N_MAX * N_MAX];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, m, answer, num;

    static void dfs(int x, int y) {
        if (colors[x][y] != 0)
            return;
        colors[x][y] = num;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
                if (map[nx][ny] == map[x][y])
                    dfs(nx, ny);
                if (map[nx][ny] < map[x][y])
                    s[num] = 1;
            }
        }
    }

    static void calculate() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (colors[i][j] == 0) {
                    num++;
                    dfs(i, j);
                }
            }
        }

        for (int i = 1; i <= num; i++) {
            if (s[i] == 0)
                answer++;
        }
    }

    public static void main(String[] args) throws Exception {
        Arrays.stream(map).forEach(a -> Arrays.fill(a, Integer.MAX_VALUE));
        Arrays.stream(colors).forEach(a -> Arrays.fill(a, 0));
        Arrays.fill(s, 0);
        answer = 0;
        num = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = reader.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        for (int i = 1; i <= n; i++) {
            String[] s = reader.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(s[j-1]);
            }
        }

        calculate();

        System.out.println(answer);

        reader.close();
    }
}

