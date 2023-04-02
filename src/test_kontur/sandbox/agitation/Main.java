package test_kontur.sandbox.agitation;

/*E. Агитация
ограничение по времени на тест2 секунды
ограничение по памяти на тест256 мегабайт

В уездном городе Е. скоро пройдут выборы. Штаб одного из кандидатов напечатал плакаты, агитирующие за своего кандидата.
В штабе работает студент Архип, ему-то и поручили разместить плакаты на улицах города.

В первый день Архип ходил по городу и наклеивал плакаты на стены тех зданий, которые ему понравились.
Но руководитель штаба остался недоволен работой Архипа. Поэтому утром второго дня руководитель написал на каждом плакате,
на какой улице его нужно разместить, и сказал Архипу расклеивать плакаты ровно в том порядке, в котором они лежат в стопке.

Архип решил действовать по следующему алгоритму:

Если стопка не пуста, взять из неё очередной плакат.
Прочитать на плакате название улицы, на которой его нужно разместить.
Пойти на указанную улицу и обходить все здания на ней по возрастанию номеров, начиная с номера 1, до тех пор,
пока он не дойдёт до здания, на котором ещё нет плаката (т.е. плакат не был наклеен ни в первый, ни во второй день).
Наклеить плакат на стену этого здания и вернуться к шагу 1.
Определите для каждого плаката из стопки номер здания на улице, на котором он будет размещён.
Можете считать, что на каждой улице города Е. достаточное количество зданий, и все они занумерованы последовательными
натуральными числами, начиная с единицы, без пропусков и дублей.

Входные данные
В первой строке записано целое число n — количество зданий, на которых Архип разместил плакаты в первый день (0≤n≤90000).
Далее в n строках перечислены адреса этих зданий. Каждый адрес — это название улицы и номер дома, которые склеены в одну строку
без пробелов и прочих разделителей. Название улицы состоит из строчных латинских букв и имеет длину от 1 до 1500.
Номер дома — это целое число от 1 до 10^6 без ведущих нулей. Все адреса попарно различны.
В следующей строке записано целое число m — количество плакатов в стопке на второй день (1≤m≤300000).
Далее в m строках перечислены названия улиц, на которых следует разместить эти плакаты (именно в таком порядке).
Названия улиц состоят из строчных латинских букв и имеют длину от 1 до 1500.
Суммарный размер входных данных не превышает одного мегабайта.

Выходные данные
Для каждого плаката в стопке определите номер здания на соответствующей улице, на котором он будет размещён.

Пример
входные данные
3
mira32
turgeneva4
mira1
6
mira
turgeneva
turgeneva
turgeneva
turgeneva
lenina
выходные данные
2
1
2
3
5
1*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        // храним уже размещенные плакаты на каждой улице
        Map<String, Set<Integer>> lastPosters = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String address = reader.readLine();
            String street = address.replaceAll("[0-9]+", "");
            int houseNumber = Integer.parseInt(address.replaceAll("[^0-9]+", ""));
            Set<Integer> set = lastPosters.getOrDefault(street, new HashSet<>());
            set.add(houseNumber);
            lastPosters.put(street, set);
        }

        int m = Integer.parseInt(reader.readLine());
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            queue.offer(reader.readLine());
        }

        while (!queue.isEmpty()) {
            String street = queue.poll();
            Set<Integer> set = lastPosters.getOrDefault(street, new HashSet<>());
            int num = findFirstMissingNumber(set);
            set.add(num);
            lastPosters.put(street, set);
            System.out.println(num);
        }
    }

    /*private static int findFirstMissingNumber(Set<Integer> set) {
        int left = 1;
        int right = 1_000_000;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (set.contains(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }*/

    private static int findFirstMissingNumber(Set<Integer> set) {
        int left = 1;
        int right = 1_000_000;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (set.contains(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
