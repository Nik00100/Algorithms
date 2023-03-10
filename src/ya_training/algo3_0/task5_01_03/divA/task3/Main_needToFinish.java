package ya_training.algo3_0.task5_01_03.divA.task3;

import java.io.*;

public class Main_needToFinish {
    private final static int UNDEF = 0;
    private final static int RED = 1;
    private final static int GREEN = 2;
    private static Point[] points;
    private static int[] answer;

    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean dfs(int cur, int color, int[] colors, double middle) {
        int n = colors.length;
        while (cur < n) {
            if (colors[cur] == UNDEF) {
                colors[cur] = color;
                for (int next = 0; next < n; next++) {
                    if (cur != next && distance(points[cur], points[next]) < middle) {
                        if (colors[next] == UNDEF) {
                            dfs(next, 3 - color, colors, middle);
                        } else if (colors[next] == color) {
                            return false;
                        }
                    }
                }
            }
            cur++;
        }
        return true;
    }

    static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return dx * dx + dy * dy;
    }

    static double binSearch(double left, double right, int n) {
        while (left + 1e-10 < right) {
            double middle = (left + right) / 2;
            int[] colors = new int[n];
            if (!dfs(0, RED, colors, middle)) {
                right = middle;
            } else {
                left = middle;
                answer = colors;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        int n = Integer.parseInt(reader.readLine());
        points = new Point[n];

        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            double x = Double.parseDouble(s[0]);
            double y = Double.parseDouble(s[1]);
            points[i] = new Point(x, y);
        }

        double left = 0D;
        double right = 10_000_000D;

        System.out.printf("%.10f\n", Math.sqrt(binSearch(left, right, n)) / 2);
        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());

        reader.close();
    }
}
