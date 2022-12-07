package task4Triangles;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader inputReader = new InputReader(inputStream);
        OutputWriter outputWriter = new OutputWriter(outputStream);
        Triangles triangles = new Triangles();
        triangles.solve(inputReader, outputWriter);

    }

    static class Triangles {
        double[] diagonals;

        public void solve(InputReader in, OutputWriter out) throws IOException {
            int n = in.readIntFromLine();

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i + 1;
            }

            getDiagonals(n);
            out.println(getMaxArea(getTriangles(nums)));

            in.close();
            out.close();
        }

        private List<List<Triangle>> getTriangles(int[] vertices) {
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

        private void dfs(int[] vertices, int i, int k, int j, List<Triangle> path) {
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

        private boolean correctTriangle(List<Triangle> path, int first, int second, int third) {
            boolean flag = true;
            for (Triangle triangle : path) {
                boolean f = triangle.first != first && triangle.first != second && triangle.first != third;
                boolean s = triangle.second != first && triangle.second != second && triangle.second != third;
                boolean t = triangle.third != first && triangle.third != second && triangle.third != third;
                flag = flag && f && s && t;
            }
            return flag;
        }

        private void getDiagonals (int n) {
            diagonals = new double[n];
            diagonals[0] = 0d;
            diagonals[1] = 1d;
            diagonals[n-1] = 1d;
            double a = 1d;
            double alpha = Math.toRadians(180/n);
            for (int i = 2; i<=n/2; i++) {
                double temp = diagonals[i-1];
                double discriminant = a*a - Math.pow(temp*Math.sin(alpha),2);
                double x = temp*Math.cos(alpha) + Math.sqrt(discriminant);
                diagonals[i] = x;
                diagonals[n-i] = x;
            }
        }

        private double getArea (Triangle triangle) {
            double a = diagonals[Math.abs(triangle.first - triangle.second)];
            double b = diagonals[Math.abs(triangle.third - triangle.second)];
            double c = diagonals[Math.abs(triangle.first - triangle.third)];
            double p = (a + b +c)/2;
            return Math.sqrt(p*(p-a)*(p-b)*(p-c));
        }

        private double getMaxArea (List<List<Triangle>> triangles) {
            return triangles.stream()
                    .mapToDouble(triangleList -> {
                        double sumOfAreas = 0d;
                        for (Triangle triangle : triangleList) {
                            sumOfAreas += getArea(triangle);
                        }
                        double scale = Math.pow(10, 6);
                        return Math.ceil(sumOfAreas * scale) / scale;})
                    .max().getAsDouble();
        }

    }

    static class Triangle {
        int first;
        int second;
        int third;

        public Triangle(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public String toString() {
            return "{" + first + ", " + second + ", " + third + "}";
        }

    }


    static class InputReader {
        private InputStream stream;

        private BufferedReader bufferedReader;

        public InputReader(InputStream stream) {
            this.bufferedReader = new BufferedReader(new InputStreamReader(stream));
            this.stream = stream;
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public int readIntFromLine() throws IOException {
            return Integer.parseInt(bufferedReader.readLine());
        }

        public void close() throws IOException {
            bufferedReader.close();
            stream.close();
        }
    }

    static class OutputWriter {
        private OutputStream outputStream;
        private PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            this.outputStream = outputStream;
            this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public void print(int n) {
            writer.write(String.valueOf(n));
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print("");
                }
                writer.print(objects[i].toString());
            }
        }

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() throws IOException {
            writer.close();
            outputStream.close();
        }
    }
}
