package ya_training.algo3_0.task6_06_03.task1;

/*В неориентированном графе требуется найти длину минимального пути между двумя вершинами.

Формат ввода
Первым на вход поступает число N – количество вершин в графе (1 ≤ N ≤ 100). Затем записана матрица смежности
(0 обозначает отсутствие ребра, 1 – наличие ребра). Далее задаются номера двух вершин – начальной и конечной.

Формат вывода
Выведите L – длину кратчайшего пути (количество ребер, которые нужно пройти).

Если пути нет, нужно вывести -1.

Пример 1
Ввод	Вывод
10
0 1 0 0 0 0 0 0 0 0
1 0 0 1 1 0 1 0 0 0
0 0 0 0 1 0 0 0 1 0
0 1 0 0 0 0 1 0 0 0
0 1 1 0 0 0 0 0 0 1
0 0 0 0 0 0 1 0 0 1
0 1 0 1 0 1 0 0 0 0
0 0 0 0 0 0 0 0 1 0
0 0 1 0 0 0 0 1 0 0
0 0 0 0 1 1 0 0 0 0
5 4

2
Пример 2
Ввод	Вывод
5
0 1 0 0 1
1 0 1 0 0
0 1 0 0 0
0 0 0 0 0
1 0 0 0 0
3 5

3*/

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
