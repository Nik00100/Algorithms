package ya_training.algo1_0.task4.countWords;

/*Во входном файле (вы можете читать данные из файла input.txt) записан текст. Словом считается последовательность непробельных
символов идущих подряд, слова разделены одним или большим числом пробелов или символами конца строки.
Для каждого слова из этого текста подсчитайте, сколько раз оно встречалось в этом тексте ранее.

Формат ввода
Вводится текст.

Формат вывода
Выведите ответ на задачу.

Пример 1
Ввод	Вывод
one two one tho three

0 0 1 0 0
Пример 2
Ввод	Вывод
She sells sea shells on the sea shore;
The shells that she sells are sea shells I'm sure.
So if she sells sea shells on the sea shore,
I'm sure that the shells are sea shore shells.

0 0 0 0 0 0 1 0 0 1 0 0 1 0 2 2 0 0 0 0 1 2 3 3 1 1 4 0 1 0 1 2 4 1 5 0 0
Пример 3
Ввод	Вывод
aba aba; aba @?"
0 0 1 0*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input7.txt")));
        Map<String,Integer> map = new HashMap<>();
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            String[] str = s.split(" ");
            for (String S : str) {
                if (!map.containsKey(S)) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(map.get(S)).append(" ");
                }
                map.put(S, map.getOrDefault(S,0)+1);
            }
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());

        reader.close();
    }
}
