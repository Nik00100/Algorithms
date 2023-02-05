package ya_training.algo1_0.task4.shop;

/*Дана база данных о продажах некоторого интернет-магазина. Каждая строка входного файла представляет собой запись вида
Покупатель товар количество, где Покупатель — имя покупателя (строка без пробелов), товар — название товара (строка без пробелов),
количество — количество приобретенных единиц товара. Создайте список всех покупателей, а для каждого покупателя подсчитайте
количество приобретенных им единиц каждого вида товаров.

Формат ввода
Вводятся сведения о покупках в указанном формате.

Формат вывода
Выведите список всех покупателей в лексикографическом порядке, после имени каждого покупателя выведите двоеточие,
затем выведите список названий всех приобретенных данным покупателем товаров в лексикографическом порядке, после
названия каждого товара выведите количество единиц товара, приобретенных данным покупателем. Информация о каждом
товаре выводится в отдельной строке.

Пример 1
Ввод
Ivanov paper 10
Petrov pens 5
Ivanov marker 3
Ivanov paper 7
Petrov envelope 20
Ivanov envelope 5

Вывод
Ivanov:
envelope 5
marker 3
paper 17
Petrov:
envelope 20
pens 5


Пример 2
Ввод
Ivanov aaa 1
Petrov aaa 2
Sidorov aaa 3
Ivanov aaa 6
Petrov aaa 7
Sidorov aaa 8
Ivanov bbb 3
Petrov bbb 7
Sidorov aaa 345
Ivanov ccc 45
Petrov ddd 34
Ziborov eee 234
Ivanov aaa 45

Вывод
Ivanov:
aaa 52
bbb 3
ccc 45
Petrov:
aaa 9
bbb 7
ddd 34
Sidorov:
aaa 356
Ziborov:
eee 234

Пример 3
Ввод
в файле input7
Вывод
TKSNUU:
FKXYPUGQ 49769497*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input7.txt")));
        Map<String,Map<String,Long>> map = new TreeMap<>();

        String str;

        while ((str = reader.readLine()) != null) {
            String[] s = str.split(" ");
            String name = s[0];
            String product = s[1];
            int val = Integer.parseInt(s[2]);
            Map<String,Long> prodMap = map.getOrDefault(name,new TreeMap<>());
            prodMap.put(product,prodMap.getOrDefault(product,0L) + val);
            map.put(name,prodMap);
        }

        for (Map.Entry<String,Map<String,Long>> entry : map.entrySet()) {
            StringBuilder sb = new StringBuilder();

            sb.append(entry.getKey()).append(":");
            System.out.println(sb.toString());
            sb.delete(0,sb.length());

            for (Map.Entry<String,Long> e : map.get(entry.getKey()).entrySet()) {
                String product = e.getKey();
                long val = e.getValue();
                sb.append(product).append(" ").append(val);
                System.out.println(sb.toString());
                sb.delete(0,sb.length());
            }
        }


        reader.close();
    }
}
