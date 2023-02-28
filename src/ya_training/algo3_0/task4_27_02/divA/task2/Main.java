package ya_training.algo3_0.task4_27_02.divA.task2;

/*Дана текстовая строка. С ней можно выполнять следующие операции:
1. Заменить один символ строки на другой символ.
2. Удалить один произвольный символ.
3. Вставить произвольный символ в произвольное место строки.
Например, при помощи первой операции из строки «СОК» можно получить строку «СУК», при помощи второй операци — строку «ОК»,
при помощи третьей операции — строку «СТОК».
Минимальное количество таких операций, при помощи которых можно из одной строки получить другую, называется стоимостью
редактирования или расстоянием Левенштейна.
Определите расстояние Левенштейна для двух данных строк.

Формат ввода
Программа получает на вход две строки, длина каждой из которых не превосходит 1000 символов, строки состоят только из заглавных
латинских букв.

Формат вывода
Требуется вывести одно число — расстояние Левенштейна для данных строк.

Пример
Ввод	Вывод
ABCDEFGH
ACDEXGIH
3*/

import java.io.*;
import java.util.Scanner;

public class Main {

    static int minDistance(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }

    static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int dp[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j > 0) {
                    dp[i][j] = j;
                    continue;
                }
                if (i > 0 && j == 0) {
                    dp[i][j] = i;
                    continue;
                }
                if (j > 0 && i > 0) {
                    dp[i][j] = minDistance(dp[i][j - 1] + 1, dp[i - 1][j] + 1, dp[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1));
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        FileWriter fileWriter = new FileWriter(new File("output.txt"));

        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        fileWriter.write(editDistance(s1, s2) + "");
        fileWriter.flush();
    }
}

