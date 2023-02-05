package ya_training.algo1_0.task3.genom;

/*Геном жителей системы Тау Кита содержит 26 видов оснований, для обозначения которых будем использовать буквы латинского
алфавита от A до Z, а сам геном записывается строкой из латинских букв. Важную роль в геноме играют пары соседних оснований,
например, в геноме «ABBACAB» можно выделить следующие пары оснований: AB, BB, BA, AC, CA, AB.

Степенью близости одного генома другому геному называется количество пар соседних оснований первого генома, которые
встречаются во втором геноме.

Вам даны два генома, определите степень близости первого генома второму геному. Программа получает на вход две строки,
состоящие из заглавных латинских букв. Каждая строка непустая, и её длина не превосходит 105.

Программа должна вывести одно целое число – степень близости генома, записанного в первой строке, геному, записанному
во второй строке.

Пример
Ввод
ABBACAB
BCABB

Вывод
4*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = reader.readLine();
        String s2 = reader.readLine();

        Map<String,Integer> dict = new HashMap<>();


        char prev = '\\';
        StringBuilder sb = new StringBuilder();
        for (char ch : s1.toCharArray()) {
            if (prev == '\\') {
                prev = ch;
                continue;
            }
            sb.append(prev).append(ch);
            dict.put(sb.toString(), dict.getOrDefault(sb.toString(),0) + 1);
            prev = ch;
            sb.delete(0,sb.length());
        }

        prev = '\\';
        sb.delete(0,sb.length());
        int count = 0;
        for (char ch : s2.toCharArray()) {
            if (prev == '\\') {
                prev = ch;
                continue;
            }
            sb.append(prev).append(ch);
            count += dict.getOrDefault(sb.toString(),0) ;
            dict.put(sb.toString(),0);
            prev = ch;
            sb.delete(0,sb.length());
        }

        System.out.println(count);

        reader.close();
    }
}
