package ya_training.algo1_0.task4.dict;

/*Вам дан словарь, состоящий из пар слов. Каждое слово является синонимом к парному ему слову. Все слова в словаре различны.
Для одного данного слова определите его синоним.

Формат ввода
Программа получает на вход количество пар синонимов N. Далее следует N строк, каждая строка содержит ровно два слова-синонима.
После этого следует одно слово.

Формат вывода
Программа должна вывести синоним к данному слову. Примечание

Эту задачу можно решить и без словарей (сохранив все входные данные в списке), но решение со словарем будет более простым.

Пример 1
Ввод
3
Hello Hi
Bye Goodbye
List Array
Goodbye
Вывод
Bye
Пример 2
Ввод
1
beep Car
Car
Вывод
beep
Пример 3
Ввод
2
Ololo Ololo
Numbers 1234567890
Numbers
Вывод
1234567890*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Map<String, String> map = new HashMap<>();

        for (int i=0; i<n; i++) {
            String[] s = reader.readLine().split(" ");
            map.put(s[0],s[1]);
        }

        String search = reader.readLine();

        if (!map.containsKey(search)) {
            for (Map.Entry<String,String>  entry: map.entrySet()) {
                String val = entry.getValue();
                if (val.equals(search)) {
                    System.out.println(entry.getKey());
                    break;
                }
            }
        } else {
            System.out.println(map.get(search));
        }

        reader.close();
    }
}
