package ya_fastTrack.optimal_load;

/*Ящики нужно доставить.

Имеется N ящиков по 10 кг и L ящиков по 50 кг, а также M грузовиков, пронумерованных от 1 до M. Грузоподъёмность ограничена:
грузовик с номером i может везти не более Gi килограммов.
Найдите минимальное число грузовиков, необходимое для доставки всех имеющихся ящиков за один раз, или определите,
что это невозможно.

Формат ввода
В первой строке целые числа N, L, M (0≤ N, L ≤10^6; 1 ≤ M ≤200000). Во второй строке M целых чисел через пробел:
Gi (10≤ Gi ≤10^9).

Формат вывода
В единственной строке
k — минимальное возможное число грузовиков, или число −1, если все ящики погрузить невозможно.

Пример 1
Ввод
2 1 4
10 52 42 11
Вывод
2

Пример 2
Ввод
13 0 5
42 47 40 49 42

Вывод
4

Пример 3
Ввод
0 1 1
49

Вывод
-1

Ограничение памяти 256.0 Мб
Ограничение времени 3 с
Ввод стандартный ввод или input.txt
Вывод стандартный вывод или output.txt*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nlm = reader.readLine().split(" ");

        int n = Integer.parseInt(nlm[0]);
        int l = Integer.parseInt(nlm[1]);
        int m = Integer.parseInt(nlm[2]);

        List<Integer> listM = new ArrayList<>();
        String[] sM = reader.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            listM.add(Integer.parseInt(sM[i]));
        }

        Collections.sort(listM, Collections.reverseOrder());
        //System.out.println(listM);

        int count = 0;
        for (int i = 0; i < m; i++) {
            if (l == 0 && n == 0) break;
            while (listM.get(i) >= 50 && l > 0) {  // проверка для ящиков по 50кг
                int newVal = listM.get(i) - 50;
                listM.set(i, newVal);
                l--;
            }
            while (listM.get(i) >= 10 && n > 0) {  // проверка для ящиков по 10кг
                int newVal = listM.get(i) - 10;
                listM.set(i, newVal);
                n--;
            }
            count++;
        }

        if (l == 0 && n == 0) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }

        reader.close();
    }

}
