package ya_training.algo3_0.task6_06_03.task3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main_Slow {
    static int n;
    static int m;
    static int s;
    static int t;
    static int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};
    static int bfs(int x, int y) {
        int[][] board = new int[n][m];
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
        board[x][y] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x * m + y);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int curX = cur / m;
            int curY = cur % m;

            for (int i = 0; i < 8; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                    continue;
                }

                if (board[nextX][nextY] == -1) {
                    board[nextX][nextY] = board[curX][curY] + 1;
                    queue.add(nextX * m + nextY);

                    //System.out.println(nextX + " " + nextY);
                }
            }
        }

        //printMatrix(board);

        return board[s][t];
    }

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String[] str = reader.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        s = Integer.parseInt(str[2]) - 1;
        t = Integer.parseInt(str[3]) - 1;
        int q = Integer.parseInt(str[4]);

        int result = 0;
        for (int i = 0; i < q; i++) {
            String[] xy = reader.readLine().split(" ");
            int x = Integer.parseInt(xy[0]) - 1;
            int y = Integer.parseInt(xy[1]) - 1;
            if (bfs(x, y) != -1) {
                result += bfs(x, y);
            } else {
                result = -1;
                break;
            }
        }

        System.out.println(result);

        reader.close();
    }
}

