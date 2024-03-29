package ya_training.algo1_0.task5.robot;

/*Студенты одного из вузов спроектировали робота для частичной автоматизации процесса сборки авиационного двигателя.
В процессе сборки двигателя могут встречаться операции 26 типов, которые обозначаются строчными буквами латинского алфавита.
Процесс сборки состоит из N операций.
Предполагается использовать робота один раз для выполнения части подряд идущих операций из процесса сборки.
Память робота состоит из K ячеек, каждая из которых содержит одну операцию. Операции выполняются последовательно,
начиная с первой, в том порядке, в котором они расположены в памяти. Выполнив последнюю из них, робот продолжает работу с первой.
Робота можно остановить после любой операции. Использование робота экономически целесообразно, если он выполнит
хотя бы K+1 операцию.

Требуется написать программу, которая по заданному процессу сборки определит количество экономически целесообразных
способов использования робота.

Формат ввода
В первой строке входного файла записано число K>0 — количество операций, которые можно записать в память робота.

Вторая строка состоит из N>K строчных латинских букв, обозначающих операции — процесс сборки двигателя.
Операции одного и того же типа обозначаются одной и той же буквой. N ≤ 200000

Формат вывода
Выходной файл должен содержать единственное целое число — количество экономически целесообразных способов использования робота.

Пример 1
Ввод
2
zabacabab

Вывод
5
Пример 2
Ввод
2
abc

Вывод
0*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(reader.readLine());
        String s = reader.readLine();

        int r = 0;
        long ans = 0;
        for (int l = 0; l<s.length() - k; l++) {
            r = Math.max(r,l + k);
            while (r < s.length() && s.charAt(r) == s.charAt(r - k)) {
                r += 1;
            }
            System.out.println(l +" "+r +" : "+(r - l - k));
            ans += r - l - k;
        }

        System.out.println(ans);

        reader.close();
    }
}
