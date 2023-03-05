package ya_training.algo3_0.task5_01_03.divA.task3.test;

import java.util.*;

public class Main {

    static double[][] dist;
    static boolean[][] vis;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] coords = new int[n][2];
        for (int i = 0; i < n; i++) {
            coords[i][0] = sc.nextInt();
            coords[i][1] = sc.nextInt();
        }
        dist = new double[n][n];
        vis = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                    vis[i][j] = true;
                } else if (!vis[i][j]) {
                    double dx = coords[i][0] - coords[j][0];
                    double dy = coords[i][1] - coords[j][1];
                    double d = Math.sqrt(dx * dx + dy * dy);
                    dist[i][j] = d;
                    dist[j][i] = d;
                    vis[i][j] = true;
                    vis[j][i] = true;
                }
            }
        }
        double res = dfs(0, new boolean[n], 0, 1, 0, 0);
        System.out.println(res);
    }

    static double dfs(int u, boolean[] used, int count, double a, double b, double c) {
        used[u] = true;
        count++;
        if (count == n) {
            double p = (a + b + c) / 2;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
        double res = 0;
        for (int v = 0; v < n; v++) {
            if (!used[v]) {
                double len = dist[u][v];
                double res1 = dfs(v, used, count, a + len, b, c);
                double res2 = dfs(v, used, count, a, b + len, c);
                double res3 = dfs(v, used, count, a, b, c + len);
                res = Math.max(res, Math.max(res1, Math.max(res2, res3)));
            }
        }
        used[u] = false;
        return res;
    }
}
