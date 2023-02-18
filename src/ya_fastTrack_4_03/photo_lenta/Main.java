package ya_fastTrack_4_03.photo_lenta;

/*Лента фотографий 50 баллов
Для разработки модуля отображения фотографий в виде вертикальной ленты решено использовать следующий алгоритм:

    выбрать k фотографий из имеющейся коллекции;
    отмасштабировать все фотографии до ширины w;
    отображать полученные фотографии в вертикальном формате одна над одной без отступов.

Определите, какую наибольшую и наименьшую высоту может иметь полученная лента из k фотографий.
Будем считать, что при масштабировании фотографии размера (w' x h') до ширины w, новая высота вычисляется по следующей формуле:
ℎ = (w x h') / w'

Формат ввода
В первой строке записано одно целое число w (640≤w≤4096). Во второй строке записаны два числа n и k (1≤ k ≤ n ≤ 10^5).
В каждой из следующих n строк записана строка wi×hi ( 640 ≤ wi, ℎi ≤ 4096). Размеры фотографий записаны без пробелов.

Формат вывода
Выведите в первой строке минимальную высоту ленты из k фотографий.
Во второй строке выведите максимальную высоту ленты из k фотографий.

Пример 1
Ввод
2000
5 3
1000x1000
1000x1500
640x930
640x1500
3000x1000

Вывод
5574
10595

Пример 2
Ввод
1000
5 5
1404x1386
1132x1110
1061x1801
1022x1519
1529x1003

Вывод
5810
5810

Пример 3
Ввод
4096
2 1
640x4096
4096x640

Вывод
640
26215

Примечание Функция 'потолок' ⌈x⌉ — это наименьшее целое, большее или равное x: ⌈x⌉ = min { n ∈ Z ∣ n ⩾ x}

Ограничение памяти 256.0 Мб
Ограничение времени 1 с
Ввод стандартный ввод или input.txt
Вывод стандартный вывод или output.txt*/

import java.io.*;
import java.util.*;

public class Main {

    static void addHeightToList(int w, int wi, int hi, List<Integer> heights) {
        double h = (double) (w * hi) / wi;
        heights.add((int) Math.ceil(h));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int w = Integer.parseInt(reader.readLine());
        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        List<Integer> heights = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split("x");
            int wi = Integer.parseInt(s[0]);
            int hi = Integer.parseInt(s[1]);
            addHeightToList(w, wi, hi, heights);
        }

        Collections.sort(heights);
        //System.out.println(heights);

        long minSum = 0;
        long maxSum = 0;

        for (int i = 0; i < k; i++) {
            minSum += heights.get(i);
            maxSum += heights.get(heights.size() - 1 - i);
        }

        System.out.println(minSum);
        System.out.println(maxSum);

        reader.close();
    }
}
