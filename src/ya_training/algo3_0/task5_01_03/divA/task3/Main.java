package ya_training.algo3_0.task5_01_03.divA.task3;

import java.util.*;

public class Main {

    static double[][] dist;
    static boolean[][] g;
    static boolean[] used;
    static int[] colors;
    static int n;
    static double maxPower = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        double[][] coords = new double[n][2];
        for (int i = 0; i < n; i++) {
            coords[i][0] = scanner.nextDouble();
            coords[i][1] = scanner.nextDouble();
        }
        dist = new double[n][n];
        g = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double d = Math.sqrt(Math.pow(coords[i][0] - coords[j][0], 2) + Math.pow(coords[i][1] - coords[j][1], 2));
                dist[i][j] = dist[j][i] = d;
                if (d <= 1) {
                    g[i][j] = g[j][i] = true;
                }
            }
        }
        colors = new int[n];
        used = new boolean[n];
        dfs(0, 1);
        System.out.printf(Locale.US, "%.10f\n", maxPower);
        for (int i = 0; i < n; i++) {
            System.out.print(colors[i] + " ");
        }
    }

    static void dfs(int v, int color) {
        used[v] = true;
        colors[v] = color;
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            if (g[v][i]) {
                if (colors[i] == 0) {
                    dfs(i, 3 - color);
                } else if (colors[i] == color) {
                    ok = false;
                }
            }
        }
        if (ok) {
            double power = 0;
            for (int i = 0; i < n; i++) {
                if (colors[i] == color) {
                    double d = Double.MAX_VALUE;
                    for (int j = 0; j < n; j++) {
                        if (colors[j] != color) {
                            d = Math.min(d, dist[i][j]);
                        }
                    }
                    power = Math.max(power, d);
                }
            }
            maxPower = Math.max(maxPower, power);
        }
    }
}
