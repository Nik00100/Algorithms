package task4Triangles;

import java.util.*;
import java.io.*;

// Основная идея - это найти все возможные наборы треугольников. Для этого использую Depth-First Search.
// Поиск веду по трем точкам, причем у стартового треугольника в наборе всегда первая точка 0.
// Вторая точка изменяется от 1 до n/2, третья от n до n/2 (n - количество вершин).
// Найденный треугольник делит многоугольник на три части, повторяю поиск для каждой части.
// Каждый треугольник формируется из диагоналей, поэтому для поиска площади треугольника необходимо найти длины диагоналей.
// Для поиска длин диагоналей использую теорему косинусов и тот факт, что углы,
// на которые делят диагонали угол многоугольника равны 180/n.
// Площадь треугольника ищу, используя формулу полупериметра.
// И остается в образованном массиве наборов треугольников найти максимальную сумму площадей.

public class Solution {

    static class Triangle {
        int first;
        int second;
        int third;

        public Triangle(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }

    static double[] diagonals;

    private static List<List<Triangle>> getTriangles(int[] vertices) {
        List<List<Triangle>> res = new ArrayList<>();
        for (int k = 1; k <= (vertices.length - 1) / 2; k++) {
            for (int j = vertices.length - 1; j > (vertices.length - 1) / 2; j--) {
                List<Triangle> path = new ArrayList<>();
                dfs(vertices, 0, k, j, path);
                if (!path.isEmpty()) res.add(path);
            }
        }
        return res;
    }

    private static void dfs(int[] vertices, int i, int k, int j, List<Triangle> path) {
        if (j < i + 2)
            return;
        if (k <= i || k >= j || k > vertices.length / 2 || j <= vertices.length / 2)
            return;
        if (correctTriangle(path, vertices[i], vertices[k], vertices[j])) {
            path.add(new Triangle(vertices[i], vertices[k], vertices[j]));
            dfs(vertices, i + 1, i + 2, k - 1, path);
            dfs(vertices, k + 1, k + 2, j - 1, path);
            dfs(vertices, j + 1, j + 2, vertices.length - 1, path);
        }
    }

    private static boolean correctTriangle(List<Triangle> path, int first, int second, int third) {
        boolean flag = true;
        for (Triangle triangle : path) {
            boolean f = triangle.first != first && triangle.first != second && triangle.first != third;
            boolean s = triangle.second != first && triangle.second != second && triangle.second != third;
            boolean t = triangle.third != first && triangle.third != second && triangle.third != third;
            flag = flag && f && s && t;
        }
        return flag;
    }

    private static void getDiagonals(int n) {
        diagonals = new double[n];
        diagonals[0] = 0d;
        diagonals[1] = 1d;
        diagonals[n - 1] = 1d;
        double a = 1d;
        double alpha = Math.toRadians(180 / n);
        for (int i = 2; i <= n / 2; i++) {
            double temp = diagonals[i - 1];
            double discriminant = a * a - Math.pow(temp * Math.sin(alpha), 2);
            double x = temp * Math.cos(alpha) + Math.sqrt(discriminant);
            diagonals[i] = x;
            diagonals[n - i] = x;
        }
    }

    private static double getArea(Triangle triangle) {
        double a = diagonals[Math.abs(triangle.first - triangle.second)];
        double b = diagonals[Math.abs(triangle.third - triangle.second)];
        double c = diagonals[Math.abs(triangle.first - triangle.third)];
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    private static double getMaxArea(List<List<Triangle>> triangles) {
        return triangles.stream()
                .mapToDouble(triangleList -> {
                    double sumOfAreas = 0d;
                    for (Triangle triangle : triangleList) {
                        sumOfAreas += getArea(triangle);
                    }
                    double scale = Math.pow(10, 6);
                    return Math.ceil(sumOfAreas * scale) / scale;
                })
                .max().getAsDouble();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        getDiagonals(n);
        System.out.println(getMaxArea(getTriangles(nums)));
    }
}
