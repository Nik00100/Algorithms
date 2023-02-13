package ya_training.algo3_0.contest_13_02.task2;

/*Красотой строки назовем максимальное число идущих подряд одинаковых букв. (красота строки abcaabdddettq равна 3)

Сделайте данную вам строку как можно более красивой, если вы можете сделать не более k операций замены символа.

Формат ввода
В первой строке записано одно целое число k (0 ≤ k ≤ 109)

Во второй строке дана непустая строчка S (|S| ≤ 2 ⋅ 105). Строчка S состоит только из маленьких латинских букв.

Формат вывода
Выведите одно число — максимально возможную красоту строчки, которую можно получить.

Пример 1
Ввод
2
abcaz

Вывод
4

Пример 2
Ввод
2
helto

Вывод
3*/

import java.io.*;
import java.util.*;

public class Main {
    // Our window is defined as [j, i], in the end the difference i - j will be our answer.
    // If we find charInLongestBeautySeq, we decrease kFlip. If k falls below zero, we advance j and update kFlip
    static int longestBeautySeq(String s, int kFlip, char charInLongestBeautySeq) {
        int i = 0, j = 0;
        while (i < s.length()) {
            kFlip -= s.charAt(i++) == charInLongestBeautySeq ? 0 : 1;
            if (kFlip < 0)
                kFlip += s.charAt(j++) == charInLongestBeautySeq ? 0 : 1;
        }
        return i - j;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(reader.readLine());
        String s = reader.readLine();

        Set<Character> chars = new HashSet<>();
        for (char ch : s.toCharArray()) {
            chars.add(ch);
        }

        int ans = k + 1;
        for (char ch : chars)
            ans = Math.max(ans, longestBeautySeq(s, k , ch));

        System.out.println(ans);

        reader.close();
    }
}
