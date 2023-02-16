package ya_training.algo3_0.task1_15_02.task5;

/*Лайнландия представляет из себя одномерный мир, являющийся прямой, на котором распологаются N городов, последовательно
пронумерованных от 0 до N - 1 . Направление в сторону от первого города к нулевому названо западным, а в обратную — восточным.
Когда в Лайнландии неожиданно начался кризис, все были жители мира стали испытывать глубокое смятение. По всей Лайнландии
стали ходить слухи, что на востоке живётся лучше, чем на западе.
Так и началось Великое Лайнландское переселение. Обитатели мира целыми городами отправились на восток, покинув родные улицы,
и двигались до тех пор, пока не приходили в город, в котором средняя цена проживания была меньше, чем в родном.

Формат ввода
В первой строке дано одно число N (2≤N≤105) — количество городов в Лайнландии. Во второй строке дано N чисел
ai(0≤ai≤109) — средняя цена проживания в городах с нулевого по (N - 1)-ый соответственно.
Формат вывода
Для каждого города в порядке с нулевого по (N - 1)-ый выведите номер города, в который переселятся его изначальные жители.
Если жители города не остановятся в каком-либо другом городе, отправившись в Восточное Бесконечное Ничто, выведите -1 .
Пример
Ввод	Вывод
10
1 2 3 2 1 4 2 5 3 1
-1 4 3 4 -1 6 9 8 9 -1*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");
        Stack<Integer> stack = new Stack<>();

        int[] nums = new int[n];
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(s[i]);
            nums[i] = num;
        }

        //System.out.println(Arrays.stream(nums).boxed().toList());

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()])
                answer[stack.pop()] = i;
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        //System.out.println(Arrays.stream(answer).boxed().toList());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(answer[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

        reader.close();
    }
}
