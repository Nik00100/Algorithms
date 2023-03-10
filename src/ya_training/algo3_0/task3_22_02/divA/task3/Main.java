package ya_training.algo3_0.task3_22_02.divA.task3;

/*Рассмотрим фигуру, аналогичную показанной на рисунке (большой равносторонний треугольник, составленный из маленьких
равносторонних треугольников). На рисунке приведена фигура, состоящая из 3-х уровней треугольников.
           /_\
         /_\ /_\
        /_\/_\/_\
Напишите программу, которая будет определять, сколько всего в ней треугольников (необходимо учитывать не только «маленькие»
треугольники, а вообще все треугольники — в частности, нас интересуют треугольник, выделенный жирным, вся фигура).

Формат ввода
Вводится одно число N — количество уровней в фигуре (1≤N≤100000)
Формат вывода
Выведите количество треугольников в такой фигуре.
Пример 1
Ввод	Вывод
1
1
Пример 2
Ввод	Вывод
2
5
Пример 3
Ввод	Вывод
4
27*/

import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = n / 2;

        BigInteger s = BigInteger.ZERO;
        BigInteger kBigInt = BigInteger.valueOf(k);
        BigInteger four = BigInteger.valueOf(4);
        BigInteger seven = BigInteger.valueOf(7);

        if (n % 2 == 0) {
            BigInteger numerator = kBigInt.multiply(kBigInt.add(BigInteger.ONE))
                    .multiply(four.multiply(kBigInt).add(BigInteger.ONE));
            s = numerator.divide(BigInteger.TWO);
        } else {
            BigInteger numerator = kBigInt.add(BigInteger.ONE)
                    .multiply(four.multiply(kBigInt).multiply(kBigInt)
                            .add(seven.multiply(kBigInt)).add(BigInteger.TWO));
            s = numerator.divide(BigInteger.TWO);
        }

        System.out.println(s);
    }

}
