package ya_training.algo3_0.task4_27_02.task5;

/*Даны две последовательности, требуется найти и вывести их наибольшую общую подпоследовательность.

Формат ввода
В первой строке входных данных содержится число N – длина первой последовательности (1 ≤ N ≤ 1000). Во второй строке заданы
члены первой последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.

В третьей строке записано число M – длина второй последовательности (1 ≤ M ≤ 1000). В четвертой строке задаются члены второй
последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.

Формат вывода
Требуется вывести наибольшую общую подпоследовательность данных последовательностей, через пробел.

Пример 1
Ввод	Вывод
3
1 2 3
3
2 3 1

2 3
Пример 2
Ввод	Вывод
3
1 2 3
3
3 2 1

1 */

import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> longestCommonSubsequence(List<Integer> seq1, List<Integer> seq2) {
        int[][] dp = new int[seq1.size() + 1][seq2.size() + 1];
        for (int i = 0; i < seq1.size(); ++i) {
            for (int j = 0; j < seq2.size(); ++j) {
                if (seq1.get(i).equals(seq2.get(j)) ) dp[i + 1][j + 1] = 1 + dp[i][j];
                else
                    dp[i + 1][j + 1] =  Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }

        System.out.println(dp[seq1.size()][seq2.size()]);

        List<Integer> answer = new ArrayList<>();

        int i = seq1.size();
        int j = seq2.size();

        while (i != 0 && j != 0) {
            if (seq1.get(i - 1).equals(seq2.get(j - 1))) {
                answer.add(seq1.get(i - 1));
                --i;
                --j;
            } else if (dp[i ][j - 1] > dp[i - 1][j]) {
                --j;
            } else {
                --i;
            }
        }


        Collections.reverse(answer);
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> seq1 = new ArrayList<>();
        List<Integer> seq2 = new ArrayList<>();

        int N = Integer.parseInt(reader.readLine().trim());
        String[] line1 = reader.readLine().split(" ");
        for (int i = 0; i < N; ++i) {
            seq1.add(Integer.parseInt(line1[i]));
        }

        System.out.println(seq1);

        int M = Integer.parseInt(reader.readLine().trim());
        String[] line2 = reader.readLine().split(" ");
        for (int i = 0; i < M; ++i) {
            seq2.add(Integer.parseInt(line2[i]));
        }

        List<Integer> answer = longestCommonSubsequence(seq1, seq2);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.size(); ++i) {
            sb.append(answer.get(i)).append(" ");
        }
        System.out.println(sb.toString().trim());

        reader.close();
    }
}
