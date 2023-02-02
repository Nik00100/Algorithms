package training.algo1_0.task2.sequenceType;

/*По последовательности чисел во входных данных определите ее вид:

CONSTANT – последовательность состоит из одинаковых значений
ASCENDING – последовательность является строго возрастающей
WEAKLY ASCENDING – последовательность является нестрого возрастающей
DESCENDING – последовательность является строго убывающей
WEAKLY DESCENDING – последовательность является нестрого убывающей
RANDOM – последовательность не принадлежит ни к одному из вышеупомянутых типов
Формат ввода
По одному на строке поступают числа последовательности ai, |ai| ≤ 109.

Признаком окончания последовательности является число -2× 109. Оно в последовательность не входит.

Формат вывода
В единственной строке выведите тип последовательности.

Пример
Ввод	Вывод
-530
-530
-530
-530
-530
-530
-2000000000
CONSTANT
*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine());

        Map<String,Boolean> map = new HashMap<>();
        map.put("CONSTANT",true);
        map.put("ASCENDING",true);
        map.put("WEAKLY ASCENDING",true);
        map.put("DESCENDING",true);
        map.put("WEAKLY DESCENDING",true);
        map.put("RANDOM",true);

        while (true) {
            int temp = Integer.parseInt(reader.readLine());
            if (temp == -2000000000) break;
            if (num == temp) {
                map.put("ASCENDING",false);
                map.put("DESCENDING",false);
            } else if (temp > num) {
                map.put("WEAKLY DESCENDING",false);
                map.put("DESCENDING",false);
                map.put("CONSTANT",false);
            } else {
                map.put("WEAKLY ASCENDING",false);
                map.put("ASCENDING",false);
                map.put("CONSTANT",false);
            }
            num = temp;

        }

        //System.out.println(map.entrySet().stream().collect(Collectors.toList()));

        if (map.entrySet().stream().filter(entry-> entry.getValue() == true).count() == 1) {
            System.out.println("RANDOM");
        } else {
            if (map.get("CONSTANT")) {
                System.out.println("CONSTANT");
            } else if (map.get("ASCENDING")) {
                System.out.println("ASCENDING");
            } else if (map.get("DESCENDING")) {
                System.out.println("DESCENDING");
            } else {
                map.put("RANDOM",false);
                for (String key : map.keySet()) {
                    if (map.get(key)) {
                        System.out.println(key);
                    }
                }
            }
        }

        reader.close();
    }
}
