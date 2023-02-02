package training.algo1_0.task2.korovka;

/*Ежегодный турнир «Веселый коровяк» — по метанию коровьих лепешек на дальность — прошел 8–9 июля в селе
Крылово Осинского района Пермского края. Участники соревнований кидают «снаряд» — спрессованный навоз, выбирая его из заранее
заготовленной кучи. Желающих поупражняться в силе броска традиционно очень много — как мужчин, так и женщин. Каждую лепешку,
которую метнули участники «Веселого коровяка», внимательно осматривали женщины в костюмах коров и тщательно замеряли расстояние.
Соревнования по метанию коровьих лепешек проводятся в Пермском крае с 2009 года. К сожалению, после чемпионата потерялись записи
с фамилиями участников, остались только записи о длине броска в том порядке, в котором их совершали участники.

Тракторист Василий помнит три факта:

1) Число метров, на которое он метнул лепешку, оканчивалось на 5
2) Один из победителей чемпионата метал лепешку до Василия
3) Участник, метавший лепешку сразу после Василия, метнул ее на меньшее количество метров

Будем считать, что участник соревнования занял k-е место, если ровно (k − 1) участников чемпионата метнули лепешку строго дальше, ч
ем он.
Какое максимально высокое место мог занять Василий?

Формат ввода
Первая строка входного файла содержит целое число n — количество участников чемпионата по метанию лепешек (3 ≤ n ≤ 105).

Вторая строка входного файла содержит n положительных целых чисел, каждое из которых не превышает 1000, — дальность броска
участников чемпионата, приведенные в том порядке, в котором происходило метание.

Формат вывода
Выведите самое высокое место, которое мог занять тракторист Василий. Если не существует ни одного участника чемпионата,
который удовлетворяет, описанным выше условиям, выведите число 0.

Пример 1
Ввод
7
10 20 15 10 30 5 1
Вывод
6
Пример 2
Ввод
3
15 15 10
Вывод
1
Пример 3
Ввод
3
10 15 20
Вывод
0*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] % 10 == 5)
                map.put(arr[i],i);
        }

        if (map.size() == 0) {
            System.out.println(0);
        } else {
            int winner = -1;
            int first_place_index = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] > arr[first_place_index])
                    first_place_index = i;
            }

            for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
                int next_id = entry.getValue() + 1;
                if (next_id < n) {
                    if (first_place_index < entry.getValue() && entry.getKey() > arr[next_id]) {
                        if (winner < entry.getKey()) {
                            winner = entry.getKey();
                        }
                    }
                }
            }

            if (winner == -1) {
                System.out.println(0);
            } else {
                int[] reverse = Arrays.stream(arr).boxed()
                        .sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
                int ans = 0;
                for (int i=0; i<reverse.length; i++) {
                    if (reverse[i] == winner) {
                        ans = i + 1;
                        break;
                    }
                }
                System.out.println(ans);
            }
        }



        reader.close();
    }
}
