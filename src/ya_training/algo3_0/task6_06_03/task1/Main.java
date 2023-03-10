package ya_training.algo3_0.task6_06_03.task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // количество вершин в графе
        int[][] adjMatrix = new int[n][n]; // матрица смежности
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = scanner.nextInt();
            }
        }
        int start = scanner.nextInt(); // начальная вершина
        int end = scanner.nextInt(); // конечная вершина

        int[] dist = new int[n]; // массив расстояний от начальной вершины
        Arrays.fill(dist, -1); // инициализация расстояний значением -1
        dist[start - 1] = 0; // расстояние до начальной вершины равно 0

        Queue<Integer> queue = new LinkedList<>(); // очередь для обхода в ширину
        queue.add(start - 1);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 0; i < n; i++) {
                if (adjMatrix[current][i] == 1 && dist[i] == -1) { // если вершина смежная и не посещена
                    dist[i] = dist[current] + 1;
                    queue.add(i);
                }
            }
        }

        if (dist[end - 1] == -1) { // путь не найден
            System.out.println("-1");
        } else { // путь найден
            System.out.println(dist[end - 1]);
        }
    }
}
