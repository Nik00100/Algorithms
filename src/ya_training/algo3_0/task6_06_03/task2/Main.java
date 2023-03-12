package ya_training.algo3_0.task6_06_03.task2;

/*В неориентированном графе требуется найти минимальный путь между двумя вершинами.

Формат ввода
Первым на вход поступает число N – количество вершин в графе (1 ≤ N ≤ 100). Затем записана матрица смежности
(0 обозначает отсутствие ребра, 1 – наличие ребра). Далее задаются номера двух вершин – начальной и конечной.

Формат вывода
Выведите сначала L – длину кратчайшего пути (количество ребер, которые нужно пройти), а потом сам путь.
Если путь имеет длину 0, то его выводить не нужно, достаточно вывести длину.

Необходимо вывести путь (номера всех вершин в правильном порядке). Если пути нет, нужно вывести -1.

Пример
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
5 2 4*/

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

        int[] prev = new int[n]; // массив предыдущих вершин
        Arrays.fill(prev, -1); // инициализация предыдущих вершин значением -1

        Queue<Integer> queue = new LinkedList<>(); // очередь для обхода в ширину
        queue.add(start - 1);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 0; i < n; i++) {
                if (adjMatrix[current][i] == 1 && dist[i] == -1) { // если вершина смежная и не посещена
                    dist[i] = dist[current] + 1;
                    prev[i] = current;
                    queue.add(i);
                }
            }
        }

        if (dist[end - 1] == -1) { // путь не найден
            System.out.println("-1");
        } else { // путь найден
            System.out.println(dist[end - 1]);
            if (dist[end - 1] > 0) { // если путь не пустой
                List<Integer> path = new ArrayList<>(); // список вершин пути
                int current = end - 1;
                while (current != -1) {
                    path.add(current);
                    current = prev[current];
                }
                Collections.reverse(path); // переворачиваем список
                for (int vertex : path) {
                    System.out.print(vertex + 1 + " "); // выводим номера вершин пути
                }
                System.out.println();
            }
        }
    }
}