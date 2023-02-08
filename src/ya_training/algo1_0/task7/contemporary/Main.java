package ya_training.algo1_0.task7.contemporary;

/*Группа людей называется современниками, если был такой момент, когда они могли собраться все вместе и обсуждать какой-нибудь важный
вопрос. Для этого в тот момент, когда они собрались, каждому из них должно было уже исполниться 18 лет, но еще не исполниться 80 лет.
Вам дан список великих людей с датами их жизни. Выведите всевозможные максимальные множества современников. Множество современников
будем называть максимальным, если нет другого множества современников, которое включает в себя всех людей из первого множества.
Будем считать, что в день своего 18-летия человек уже может принимать участие в такого рода собраниях, а в день 80-летия, равно как и
в день своей смерти, — нет.

Формат ввода
Сначала на вход программы поступает число N — количество людей (1 ≤ N ≤ 10000). Далее в N строках вводится по шесть
чисел — первые три задают дату (день, месяц, год) рождения, следующие три — дату смерти (она всегда не ранее даты рождения).
День (в зависимости от месяца, а в феврале — еще и года) от 1 до 28, 29, 30 или 31, месяц — от 1 до 12, год — от 1 до 2005.

Формат вывода
Программа должна вывести все максимальные множества современников. Каждое множество должно быть записано на отдельной строке и
содержать номера людей (люди во входных данных нумеруются в порядке их задания, начиная с 1). Номера людей должны разделяться
пробелами.

Никакое множество не должно быть указано дважды.

Если нет ни одного непустого максимального множества, выведите одно число 0.

Гарантируется, что входные данные будут таковы, что размер выходных данных для правильного ответа не превысит 2 Мб.

Пример 1
Ввод
3
2 5 1988 13 11 2005
1 1 1 1 1 30
1 1 1910 1 1 1990
Вывод
2
3
Пример 2
Ввод
3
2 5 1968 13 11 2005
1 1 1 1 1 30
1 1 1910 1 1 1990
Вывод
2
1 3
Пример 3
Ввод
3
2 5 1988 13 11 2005
1 1 1 1 1 10
2 1 1910 1 1 1928
Вывод
0*/

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        int n = Integer.parseInt(reader.readLine());

        /*int compareTo0(LocalDate otherDate) {
            int cmp = (year - otherDate.year);
            if (cmp == 0) {
                cmp = (month - otherDate.month);
                if (cmp == 0) {
                    cmp = (day - otherDate.day);
                }
            }
            return cmp;
        }*/

        for (int i = 0; i < n; i++) {
            String[] dmyBorn_dmyDie = reader.readLine().split(" ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Y-M-d");

            int dBorn = Integer.parseInt(dmyBorn_dmyDie[0]);
            int mBorn = Integer.parseInt(dmyBorn_dmyDie[1]);
            int yBorn = Integer.parseInt(dmyBorn_dmyDie[2]);

            int dDie = Integer.parseInt(dmyBorn_dmyDie[3]);
            int mDie = Integer.parseInt(dmyBorn_dmyDie[4]);
            int yDie = Integer.parseInt(dmyBorn_dmyDie[5]);

            LocalDate born = LocalDate.of(yBorn, mBorn, dBorn);
            LocalDate die = LocalDate.of(yDie, mDie, dDie);

            LocalDate age18 = born.plusYears(18);

            System.out.print(age18.format(formatter));
            System.out.print(" ");
            System.out.print(die.format(formatter));
            System.out.println();

        }
    }
}
