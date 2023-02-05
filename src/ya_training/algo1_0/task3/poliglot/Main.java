package ya_training.algo1_0.task3.poliglot;

/*Каждый из N школьников некоторой школы знает Mi языков. Определите, какие языки знают все школьники и языки,
которые знает хотя бы один из школьников.

Формат ввода
Первая строка входных данных содержит количество школьников N. Далее идет N чисел Mi, после каждого из чисел идет Mi строк,
содержащих названия языков, которые знает i-й школьник. Длина названий языков не превышает 1000 символов,
количество различных языков не более 1000. 1 ≤ N ≤ 1000, 1 ≤ Mi ≤ 500.

Формат вывода
В первой строке выведите количество языков, которые знают все школьники. Начиная со второй строки - список таких языков.
Затем - количество языков, которые знает хотя бы один школьник, на следующих строках - список таких языков.

Пример
Ввод
3
3
Russian
English
Japanese
2
Russian
English
1
English
Вывод
1
English
3
Russian
Japanese
English*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        Map<String,Integer> dict = new HashMap<>();

        for (int i=0; i<n; i++) {
            int m = Integer.parseInt(reader.readLine());
            for (int j=0; j<m; j++) {
                String language = reader.readLine();
                dict.put(language, dict.getOrDefault(language,0) +1);
            }
        }
        List<String> allKnown = dict.entrySet().stream()
                .filter(entry-> entry.getValue() == n)
                .map(entry-> entry.getKey()).collect(Collectors.toList());

        System.out.println(allKnown.size());
        for (String str : allKnown) {
            System.out.println(str);
        }

        System.out.println(dict.size());
        for (String str : dict.keySet()) {
            System.out.println(str);
        }


        reader.close();

    }
}
