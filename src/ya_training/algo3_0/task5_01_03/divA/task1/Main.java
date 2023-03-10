package ya_training.algo3_0.task5_01_03.divA.task1;

/*Требуется вычислить площадь комнаты в квадратном лабиринте.

Формат ввода
В первой строке вводится число N – размер лабиринта (3 ≤ N ≤ 10). В следующих N строках задан лабиринт
(‘.’ – пустая клетка, ‘*’ – стенка). И наконец, последняя строка содержит два числа – номер строки и столбца клетки,
находящейся в комнате, площадь которой необходимо вычислить. Гарантируется, что эта клетка пустая и что лабиринт окружен
стенками со всех сторон.

Формат вывода
Требуется вывести единственное число – количество пустых клеток в данной комнате.

Пример
Ввод	Вывод
3
***
*.*
***
2 2
1*/

import java.io.*;

public class Main {
    static int n;
    static char[][] maze;
    static boolean[][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // возможные направления для движения

    static int dfs(int x, int y) {
        visited[x][y] = true;
        int area = 1; // текущая площадь комнаты

        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < n && newY >= 0 && newY < n &&
                    !visited[newX][newY] && maze[newX][newY] == '.') {
                area += dfs(newX, newY);
            }
        }
        return area;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());
        maze = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = reader.readLine();
            for (int j = 0; j < n; j++) {
                maze[i][j] = s.charAt(j);
            }
        }

        // чтение начальной клетки
        String[] xy = reader.readLine().split(" ");
        int startX = Integer.parseInt(xy[0]) - 1;
        int startY = Integer.parseInt(xy[1]) - 1;

        int area = dfs(startX, startY); // вызов DFS для вычисления площади комнаты
        System.out.println(area);

        reader.close();
    }
}
