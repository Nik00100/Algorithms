package ya_training.algo3_0.contest_13_02.task4;

/*Петя и Вася — одноклассники и лучшие друзья, поэтому они во всём помогают друг другу. Завтра у них контрольная по математике,
и учитель подготовил целых K вариантов заданий.

В классе стоит один ряд парт, за каждой из них (кроме, возможно, последней) на контрольной будут сидеть ровно два ученика.
Ученики знают, что варианты будут раздаваться строго по порядку: правый относительно учителя ученик первой парты получит вариант 1,
левый — вариант 2, правый ученик второй парты получит вариант 3 (если число вариантов больше двух) и т.д.
Так как K может быть меньше чем число учеников N, то после варианта K снова выдаётся вариант 1. На последней парте в случае
нечётного числа учеников используется только место 1.

Петя самым первым вошёл в класс и сел на своё любимое место. Вася вошёл следом и хочет получить такой же вариант, что и Петя,
при этом сидя к нему как можно ближе. То есть между ними должно оказаться как можно меньше парт, а при наличии двух таких мест
с равным расстоянием от Пети Вася сядет позади Пети, а не перед ним. Напишите программу, которая подскажет Васе, какой ряд и
какое место (справа или слева от учителя) ему следует выбрать. Если же один и тот же вариант Вася с Петей писать не смогут,
то выдайте одно число -1.

Формат ввода
В первой строке входных данных находится количество учеников в классе 2≤N≤10^9. Во второй строке — количество
подготовленных для контрольной вариантов заданий 2≤K≤N. В третьей строке — номер ряда, на который уже сел Петя,
в четвёртой — цифра 1, если он сел на правое место, и 2, если на левое.

Формат вывода
Если Вася никак не сможет писать тот же вариант, что и Петя, то выведите -1. Если решение существует, то выведите два числа —
номер ряда, на который следует сесть Васе, и 1, если ему надо сесть на правое место, или 2, если на левое.
Разрешается использовать только первые N мест в порядке раздачи вариантов.

Пример 1
Ввод
25
2
1
2

Вывод
2 2
Пример 2
Ввод
25
13
7
1

Вывод
-1
Примечания
В первом примере вариантов 2, поэтому наилучшее место для Васи находится сразу за Петей. Во втором примере Петя будет единственным,
кто получит вариант 13.*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(array-> System.out.println(Arrays.stream(array).boxed().collect(Collectors.toList())));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        int r = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());

        boolean odd = n % 2 != 0;
        int rows = (int) Math.round((double) n / 2);

        //System.out.println(rows);

        //int[][] tables = new int[493827161][2];
        int[][] tables = new int[rows][2]; // matrix for all 493827161
        // fill tables with pupils and give them variants
        int variant = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 2; j++) {
                tables[i][j] = variant;
                variant++;
                if (variant > k) {
                    variant = 1;
                }
            }
            if (variant > k) {
                variant = 1;
            }
            if (i == (rows - 1) && odd) {
                tables[i][1] = 0;
            }
        }

        printMatrix(tables);

        // Petya's variant
        int variantPetya = tables[r - 1][c - 1];

        // find all places with Petya's variant
        List<Integer> schoolDesks = new ArrayList<>(); // find row
        List<Integer> places = new ArrayList<>(); // find column
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 2; j++) {
                if (tables[i][j] == variantPetya) {
                    schoolDesks.add(i + 1);
                    places.add(j + 1);
                }
            }
        }

        //System.out.println(schoolDesks);
        //System.out.println(places);

        StringBuilder sb = new StringBuilder();
        if (k == n || (schoolDesks.size() == 1 && schoolDesks.get(0) == r)) { // bad variant
            System.out.println(-1);
        } else {
            for (int i = 0; i < schoolDesks.size(); i++) {
                if (schoolDesks.get(i) == r) { // row, where Petya sitting
                    if (schoolDesks.get(i) == schoolDesks.get(schoolDesks.size() - 1)) { // if he sit on last possible row
                        sb.append(schoolDesks.get(i - 1)).append(" ").append(places.get(i - 1));
                        System.out.println(sb.toString());
                    } else {
                        if (i > 0 && r - schoolDesks.get(i - 1) < schoolDesks.get(i + 1) - r) {
                            sb.append(schoolDesks.get(i - 1)).append(" ").append(places.get(i - 1));
                            System.out.println(sb.toString());
                        } else {
                            sb.append(schoolDesks.get(i + 1)).append(" ").append(places.get(i + 1));
                            System.out.println(sb.toString());
                        }
                    }
                }
            }
        }

        reader.close();
    }
}
