package ya_training.algo3_0.task6_06_03.divA.task1;

/*На стандартной шахматной доске (8х8) живут 2 шахматных коня: Красный и Зеленый. Обычно они беззаботно скачут по просторам доски,
пощипывая шахматную травку, но сегодня особенный день: у Зеленого коня День Рождения. Зеленый конь решил отпраздновать это
событие вместе с Красным. Но для осуществления этого прекрасного плана им нужно оказаться на одной клетке.
Заметим, что Красный и Зеленый шахматные кони сильно отличаются от черного с белым: они ходят не по очереди, а одновременно,
и если оказываются на одной клетке, никто никого не съедает. Сколько ходов им потребуется, чтобы насладиться праздником?

Формат ввода
На вход программы поступают координаты коней, записанные по стандартным шахматным правилам (т.е. двумя символами -
маленькая латинская буква (от a до h) и цифра (от 1 до 8), задающие столбец и строку соответственно).

Формат вывода
Требуется вывести наименьшее необходимое количество ходов, либо число -1, если кони не могут встретиться.

Пример 1
Ввод	Вывод
a1 a2
-1
Пример 2
Ввод	Вывод
a1 a3
1
Пример 3
Ввод	Вывод
h1 a8
3*/

import java.io.*;
import java.util.*;

public class Main {
    static final int N = 8;
    static final int M = 8;
    static int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};

    static int bfs(int[] xy, int[][] board) {
        int x = xy[0];
        int y = xy[1];

        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
        board[x][y] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(x * M + y);
        int steps = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int curX = cur / M;
            int curY = cur % M;

            for (int i = 0; i < 8; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    continue;
                }

                if (board[nextX][nextY] == -1) {
                    board[nextX][nextY] = board[curX][curY] + 1;
                    steps++;
                    queue.add(nextX * M + nextY);
                }
            }
        }

        return steps;
    }

    static int[] parse(String coord) {
        int[] ans = new int[2];
        ans[0] = coord.charAt(0) - 'a';
        ans[1] = coord.charAt(1) - '1';
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String[] str = reader.readLine().split(" ");
        int[] coord1 = parse(str[0]);
        int[] coord2 = parse(str[1]);

        int[][] board1 = new int[N][M];
        int[][] board2 = new int[N][M];
        int moves1 = bfs(coord1, board1);
        int moves2 = bfs(coord2, board2);

        int minSteps = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board1[i][j] == board2[i][j])
                    minSteps = Math.min(minSteps, board1[i][j]);
            }
        }

        System.out.println(minSteps == Integer.MAX_VALUE ? -1 : minSteps);

        reader.close();
    }
}
