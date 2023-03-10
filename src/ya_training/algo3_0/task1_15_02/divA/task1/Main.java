package ya_training.algo3_0.task1_15_02.divA.task1;

/*Для транспортирования материалов из цеха А в цех В используется конвейер. Материалы упаковываются в одинаковые контейнеры
и размещаются на ленте один за одним в порядке изготовления в цехе А. Каждый контейнер имеет степень срочности обработки в цехе В.
Для упорядочивания контейнеров по степени срочности используют накопитель, который находится в конце конвейера перед входом
в цех В. Накопитель работает пошагово, на каждом шаге возможны следующие действия:

накопитель перемещает первый контейнер из ленты в цех В;

накопитель перемещает первый контейнер из строки в склад (в складе каждый следующий контейнер помещается на предыдущий);

накопитель перемещает верхний контейнер из склада в цех В.

Напишите программу, которая по последовательности контейнеров определит, можно ли упорядочить их по степени срочности пользуясь
описанным накопителем.

Формат ввода
Входной файл в первой строке содержит количество тестов N. Далее следует N строк, каждый из которых описывает отдельный тест и
содержит целое число K (1 ≤ K ≤ 10000) — количество контейнеров в последовательности и K действительных чисел — степеней срочности
контейнеров в порядке их поступления из цеха А (меньшим числам соответствует большая степень срочности).

Формат вывода
Каждая строка выходного файла должна содержать ответ для одного теста. Необходимо вывести 1, если необходимое упорядочивание
возможно, или 0 в противном случае.

Пример
Ввод	Вывод
2
2 2.9 2.1
3 5.6 9.0 2.0

1
0*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static boolean isSortPossible(double[] tasks) {
        if (tasks == null || tasks.length == 0) {
            return true;
        }
        PriorityQueue<Double> queue = new PriorityQueue<>(Arrays.stream(tasks).boxed().collect(Collectors.toList()));

        Stack<Double> stack = new Stack<>();
        int n = tasks.length;

        boolean couldSort = true;
        double next = queue.poll();

        for (int i = 0; i < n; i++) {
            double num = tasks[i];
            if (num == next) {
                if (!queue.isEmpty()) next = queue.poll();
                while (!stack.isEmpty() && stack.peek() == next) {
                    stack.pop();
                    if (!queue.isEmpty()) next = queue.poll();
                }
                continue;
            }

            if (!stack.isEmpty() && num > stack.peek()) {
                couldSort = false;
                break;
            }

            stack.push(num);
        }
        return couldSort;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        int n = Integer.parseInt(reader.readLine());

        for (int i =0; i<n; i++) {
            String[] row = reader.readLine().split(" ");
            int k = Integer.parseInt(row[0]);
            double[] tasks = new double[k];
            for (int j =0; j<k; j++) {
                tasks[j] = Double.parseDouble(row[j + 1]);
            }
            if (isSortPossible(tasks))
                System.out.println(1);
            else
                System.out.println(0);
        }

        reader.close();
    }
}
