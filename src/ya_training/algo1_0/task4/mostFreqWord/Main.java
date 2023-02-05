package ya_training.algo1_0.task4.mostFreqWord;

/*Дан текст. Выведите слово, которое в этом тексте встречается чаще всего. Если таких слов несколько, выведите то,
которое меньше в лексикографическом порядке.

Формат ввода
Вводится текст.

Формат вывода
Выведите ответ на задачу.

Пример 1
Ввод	Вывод
apple orange banana banana orange
banana
Пример 2
Ввод	Вывод
oh you touch my tralala mmm my ding ding dong
ding
Пример 3
Ввод	Вывод
q w e r t y u i o p
a s d f g h j k l
z x c v b n m
a*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input7.txt")));
        Map<String,Integer> map = new HashMap<>();
        String s;

        int max = 0;
        while ((s = reader.readLine()) != null) {
            String[] str = s.split(" ");
            for (String S : str) {
                int val = map.getOrDefault(S,0) + 1;
                if (val > max) max = val;
                map.put(S,val);
            }
        }

        List<String> list = new ArrayList<>();
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) list.add(entry.getKey());
        }
        Collections.sort(list);
        System.out.println(list.get(0));

        reader.close();
    }
}
