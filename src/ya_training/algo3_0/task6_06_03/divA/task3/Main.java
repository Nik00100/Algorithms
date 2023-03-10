package ya_training.algo3_0.task6_06_03.divA.task3;

/*Игрушечный лабиринт представляет собой прозрачную плоскую прямоугольную коробку, внутри которой есть препятствия и
перемещается шарик. Лабиринт можно наклонять влево, вправо, к себе или от себя, после каждого наклона шарик перемещается
в заданном направлении до ближайшего препятствия или до стенки лабиринта, после чего останавливается. Целью игры является
загнать шарик в одно из специальных отверстий – выходов. Шарик проваливается в отверстие, если оно встречается на его пути
(шарик не обязан останавливаться в отверстии).

Первоначально шарик находится в левом верхнем углу лабиринта. Гарантируется, что решение существует и левый верхний угол
не занят препятствием или отверстием.

Формат ввода
В первой строке входного файла записаны числа N и M – размеры лабиринта (целые положительные числа, не превышающие 100).
Затем идет N строк по M чисел в каждой – описание лабиринта. Число 0 в описании означает свободное место, число 1 – препятствие,
число 2 – отверстие.

Формат вывода
Выведите единственное число – минимальное количество наклонов, которые необходимо сделать, чтобы шарик покинул лабиринт
через одно из отверстий.

Пример
Ввод	Вывод
4 5
0 0 0 0 1
0 1 1 0 2
0 2 1 0 0
0 0 1 0 0

3
*/

import java.util.*;

class Main {
    static class Pos {
        int i;
        int j;

        Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static boolean check(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        final int UNDEF = -1;
        int[][] len = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(len[i], UNDEF);
        }
        Queue<Pos> q = new LinkedList<>();
        len[0][0] = 0;
        q.offer(new Pos(0, 0));

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (a[cur.i][cur.j] == 2) {
                System.out.println(len[cur.i][cur.j]);
                return;
            }
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {
                    if (di * di + dj * dj == 1) {
                        int ni = cur.i;
                        int nj = cur.j;
                        while (true) {
                            if (a[ni][nj] == 2) {
                                break;
                            }
                            if (!check(ni + di,nj + dj, n, m) || a[ni + di][nj + dj] == 1) {
                                break;
                            }
                            ni += di;
                            nj += dj;
                        }
                        if (len[ni][nj] == UNDEF) {
                            len[ni][nj] = len[cur.i][cur.j] + 1;
                            q.offer(new Pos(ni, nj));
                        }
                    }
                }
            }
        }

        sc.close();
    }

}
