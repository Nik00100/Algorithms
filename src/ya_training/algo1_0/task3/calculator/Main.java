package ya_training.algo1_0.task3.calculator;

/*В новой программе OpenCalculator появилась новая возможность – можно настроить, какие кнопки отображаются, а какие – нет.
Если кнопка не отображается на экране, то ввести соответствующую цифру с клавиатуры или копированием из другой программы нельзя.
Петя настроил калькулятор так, что он отображает только кнопки с цифрами x, y, z. Напишите программу, определяющую, сможет ли Петя
ввести число N, а если нет, то какое минимальное количество кнопок надо дополнительно отобразить на экране для его ввода.

Формат ввода
Сначала вводятся три различных числа из диапазона от 0 до 9: x, y и z (числа разделяются пробелами).
Далее вводится целое неотрицательное число N, которое Петя хочет ввести в калькулятор. Число N не превышает 10000.

Формат вывода
Выведите, какое минимальное количество кнопок должно быть добавлено для того, чтобы можно было ввести число N
(если число может быть введено с помощью уже имеющихся кнопок, выведите 0)

Пример 1
Ввод	Вывод
1 2 3
1123
0
Пример 2
Ввод	Вывод
1 2 3
1001
1
Пример 3
Ввод	Вывод
5 7 3
123
2
Примечания
Комментарии к примерам тестов.

1. Число может быть введено имеющимися кнопками.

2. Нужно добавить кнопку 0.

3. Нужно добавить кнопки 1 и 2.*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = reader.readLine().split(" ");
        Integer num = Integer.parseInt(reader.readLine());

        Set<Integer> set = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            set.add(Integer.parseInt(nums[i]));
        }

        int count = 0;
        Set<Integer> setForDigits = new HashSet<>();
        while (num > 0) {
            int n = num % 10;
            if (!set.contains(n) && !setForDigits.contains(n)) count++;
            setForDigits.add(n);
            num /= 10;
        }

        System.out.println(count);

        reader.close();
    }
}
