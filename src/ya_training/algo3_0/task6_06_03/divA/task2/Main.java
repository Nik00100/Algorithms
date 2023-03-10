package ya_training.algo3_0.task6_06_03.divA.task2;

/*Витя хочет придумать новую игру с числами. В этой игре от игроков требуется преобразовывать четырехзначные числа не
содержащие нулей при помощи следующего разрешенного набора действий:
Можно увеличить первую цифру числа на 1, если она не равна 9.Можно уменьшить последнюю цифру на 1, если она не равна 1.
Можно циклически сдвинуть все цифры на одну вправо.Можно циклически сдвинуть все цифры на одну влево.
Например, применяя эти правила к числу 1234 можно получить числа 2234,1233,4123 и 2341 соответственно.
Точные правила игры Витя пока не придумал, но пока его интересует вопрос, как получить из одного числа другое
за минимальное количество операций.

Формат ввода
Во входном файле содержится два различных четырехзначных числа, каждое из которых не содержит нулей.
Формат вывода
Программа должна вывести последовательность четырехзначных чисел, не содержащих нулей.
Последовательность должна начинаться первым из данных чисел и заканчиваться вторым из данных чисел, каждое последующее
число в последовательности должно быть получено из предыдущего числа применением одного из правил. Количество чисел в
последовательности должно быть минимально возможным.

Пример
Ввод	Вывод
1234
4321
1234
2234
3234
4323
4322
4321
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int firstNumber = Integer.parseInt(reader.readLine());
        int lastNumber = Integer.parseInt(reader.readLine());

        Map<Integer, Set<Integer>> previous = new HashMap<>();// Словарь предшествующих чисел для каждого числа-ключа.
        // У одного числа может быть несколько потенциальных предшественников.
        previous.put(firstNumber, new HashSet<>());// У первого числа нет предшественников.

        Map<Integer, Integer> distances = new HashMap<>();// Словарь расстояний от первого числа до каждого последующего.
        distances.put(firstNumber, 0);

        Queue<Integer> modifications = new ArrayDeque<>();// Очередь полученных чисел.
        modifications.add(firstNumber);

        while (!distances.containsKey(lastNumber)) {// Пока искомое число не появится в словаре расстояний,
            int curNumber = modifications.remove();// берем из очереди 1-й элемент
            // и трансформируем его:
            if (curNumber / 1000 != 9) {
                int nextNumber = curNumber + 1000;
                previous.putIfAbsent(nextNumber, new HashSet<>());
                previous.get(nextNumber).add(curNumber);
                if (!distances.containsKey(nextNumber)) {// Если полученное число
                    // еще не представлено в словаре distances,
                    // добавляем его в словарь и в очередь.
                    distances.put(nextNumber, distances.get(curNumber) + 1);
                    // Расстояние до нового числа будет на 1 больше
                    // расстояния до его предшественника.
                    modifications.add(nextNumber);
                }
            }

            if (curNumber % 10 != 1) {
                int nextNumber = curNumber - 1;
                previous.putIfAbsent(nextNumber, new HashSet<>());
                previous.get(nextNumber).add(curNumber);
                if (!distances.containsKey(nextNumber)) {
                    distances.put(nextNumber, distances.get(curNumber) + 1);
                    modifications.add(nextNumber);
                }
            }

            int nextNumber = Integer.parseInt(String.valueOf(curNumber).substring(1) + String.valueOf(curNumber).charAt(0));
            previous.putIfAbsent(nextNumber, new HashSet<>());
            previous.get(nextNumber).add(curNumber);
            if (!distances.containsKey(nextNumber)) {
                distances.put(nextNumber, distances.get(curNumber) + 1);
                modifications.add(nextNumber);
            }

            nextNumber = Integer.parseInt(String.valueOf(curNumber).substring(3) + String.valueOf(curNumber).substring(0, 3));
            previous.putIfAbsent(nextNumber, new HashSet<>());
            previous.get(nextNumber).add(curNumber);
            if (!distances.containsKey(nextNumber)) {
                distances.put(nextNumber, distances.get(curNumber) + 1);
                modifications.add(nextNumber);
            }
        }

        // Список содержит путь первого числа до последнего в обратной последовательности.
        List<Integer> path = new ArrayList<>();
        int curNumber = lastNumber;
        path.add(curNumber);

        // Перебираем предшественников в словаре previous до тех пор,
        // пока не встретится первое заданное число.
        while (curNumber != firstNumber) {
            for (int number : previous.get(curNumber)) {//Среди предшественников каждого числа
                // ищем такое, которое на 1 дальше текущего числа.
                if (distances.get(curNumber) - distances.get(number) == 1) {
                    path.add(number);
                    curNumber = number;
                }
            }
        }

        //Выводим путь в обратной последовательности.
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i));
        }

        reader.close();
    }
}
