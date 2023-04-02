package test_kontur.sandbox.indus_math_done;

/*A. Индусская математика
ограничение по времени на тест 1 секунда
ограничение по памяти на тест 256 мегабайт
Пусть задано число x, десятичная запись которого не содержит нулей. Рассмотрим все числа, которые можно получить из числа x
перестановкой его цифр. Пусть M  — наибольшее из таких чисел, а m — наименьшее. Определим число K(x) как M−m.
Например, для x=6174 получаем M=7641, m=1467, K(x)=7641−1467=6174. Вычислите K(x) по заданному числу x.

Входные данные
В единственной строке записано целое число x (1≤x≤10^9−1). Десятичная запись x не содержит нулей.

Выходные данные
Выведите целое число K(x).

Система оценки
В этой задаче одна группа тестов стоимостью 10 баллов.

Пример
входные данные
6174
выходные данные
6174*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt(); // здесь нужно указать заданное число

        int[] digits = getDigits(x); // получаем цифры числа x в массиве
        int m = getSmallest(digits); // находим наименьшее число
        int M = getLargest(digits); // находим наибольшее число
        int K = M - m; // вычисляем K(x)

        System.out.println(K); // выводим результат

        scanner.close();
    }

    // метод для получения цифр числа в массиве
    private static int[] getDigits(int num) {
        String numStr = Integer.toString(num);
        int[] digits = new int[numStr.length()];

        for (int i = 0; i < numStr.length(); i++) {
            digits[i] = numStr.charAt(i) - '0';
        }

        return digits;
    }

    // метод для нахождения наименьшего числа, полученного перестановкой цифр
    private static int getSmallest(int[] digits) {
        Arrays.sort(digits);
        int num = 0;

        for (int i = 0; i < digits.length; i++) {
            num = num * 10 + digits[i];
        }

        return num;
    }

    // метод для нахождения наибольшего числа, полученного перестановкой цифр
    private static int getLargest(int[] digits) {
        Arrays.sort(digits);
        int num = 0;

        for (int i = digits.length - 1; i >= 0; i--) {
            num = num * 10 + digits[i];
        }

        return num;
    }
}
