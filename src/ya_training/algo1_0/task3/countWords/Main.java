package ya_training.algo1_0.task3.countWords;

/*Во входном файле (вы можете читать данные из sys.stdin, подключив библиотеку sys) записан текст.
Словом считается последовательность непробельных символов идущих подряд, слова разделены одним или большим числом
пробелов или символами конца строки. Определите, сколько различных слов содержится в этом тексте.

Формат ввода
Вводится текст.

Формат вывода
Выведите ответ на задачу.

Пример
Ввод	Вывод
She sells sea shells on the sea shore;
The shells that she sells are sea shells I'm sure.
So if she sells sea shells on the sea shore,
I'm sure that the shells are sea shore shells.

19*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input7.txt")));
        Set<String> set = new HashSet<>();
        String s;
        while ((s = reader.readLine()) != null) {
            String[] str = s.split(" ");
            for (String S : str) {
                if (!set.contains(S.trim()))
                    set.add(S.trim());
            }
        }

        System.out.println(set.size());

        reader.close();
    }
}
