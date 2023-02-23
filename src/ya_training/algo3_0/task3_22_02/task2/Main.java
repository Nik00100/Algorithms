package ya_training.algo3_0.task3_22_02.task2;

/*У одного из студентов в комнате живёт кузнечик, который очень любит прыгать по клетчатой одномерной доске.
Длина доски — N клеток. К его сожалению, он умеет прыгать только на 1, 2, …, k клеток вперёд.

Однажды студентам стало интересно, сколькими способами кузнечик может допрыгать из первой клетки до последней.
Помогите им ответить на этот вопрос.

Формат ввода
В первой и единственной строке входного файла записано два целых числа — N<=30 и k<=10 .

Формат вывода
Выведите одно число — количество способов, которыми кузнечик может допрыгать из первой клетки до последней.

Пример
Ввод	Вывод
8 2

21*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] s = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        if (n == 1) {
            System.out.println(1);
        } else {
            int[] dp = new int[31];
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = i-1; j >= 0; j--) {
                    dp[i] += dp[j];
                    if ((i-k) == j) break;
                }
                if (i < k) dp[i]++;
            }
            System.out.println(dp[n-2]);
        }
        reader.close();
    }
}
