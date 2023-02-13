package ya_training.algo3_0.contest_13_02.task1;

/*Вовочка ломает систему безопасности Пентагона. Для этого ему понадобилось узнать, какие символы в секретных зашифрованных
посланиях употребляются чаще других. Для удобства изучения Вовочка хочет получить графическое представление встречаемости символов.
Поэтому он хочет построить гистограмму количества символов в сообщении. Гистограмма — это график, в котором каждому символу,
встречающемуся в сообщении хотя бы один раз, соответствует столбик, высота которого пропорциональна количеству этих символов
в сообщении.

Формат ввода
Входной файл содержит зашифрованный текст сообщения. Он содержит строчные и прописные латинские буквы, цифры, знаки препинания
(«.», «!», «?», «:», «-», «,», «;», «(», «)»), пробелы и переводы строк. Размер входного файла не превышает 10000 байт.
Текст содержит хотя бы один непробельный символ. Все строки входного файла не длиннее 200 символов.
Для каждого символа c кроме пробелов и переводов строк выведите столбик из символов «#», количество которых должно быть равно
количеству символов c в данном тексте. Под каждым столбиком напишите символ, соответствующий ему. Отформатируйте гистограмму так,
чтобы нижние концы столбиков были на одной строке, первая строка и первый столбец были непустыми. Не отделяйте столбики друг от друга.
Отсортируйте столбики в порядке увеличения кодов символов.

Формат вывода
Для каждого символа c кроме пробелов и переводов строк выведите столбик из символов «#», количество которых должно быть равно
количеству символов c в данном тексте. Под каждым столбиком напишите символ, соответствующий ему. Отформатируйте гистограмму так,
чтобы нижние концы столбиков были на одной строке, первая строка и первый столбец были непустыми. Не отделяйте столбики друг от друга.
Отсортируйте столбики в порядке увеличения кодов символов.

Пример 1
Ввод
Hello, world!
Вывод
     #
     ##
#########
!,Hdelorw

Пример 2
Ввод
Twas brillig, and the slithy toves
Did gyre and gimble in the wabe;
All mimsy were the borogoves,
And the mome raths outgrabe.
Вывод
         #
         #
         #
         #
         #
         #         #
         #  #      #
      #  # ###  ####
      ## ###### ####
      ##############
      ##############  ##
#  #  ############## ###
########################
,.;ADTabdeghilmnorstuvwy*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static Map<Character, Integer> map = new HashMap<>();

    static void fillMap(String word) {
        word = word.trim();
        for (char ch : word.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
    }

    static void print () {
        int maxHeight = map.values().stream().max(Integer::compareTo).get();
        List<Character> outputList = map.keySet().stream().sorted(Character::compareTo).collect(Collectors.toList());

        for (int i=maxHeight; i>0; i--) {
            StringBuilder sb = new StringBuilder();
            for (char ch : outputList) {
                if (map.get(ch) >= i) {
                    sb.append('#');
                } else {
                    sb.append(' ');
                }
            }
            System.out.println(sb.toString());
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : outputList) {
            sb.append(ch);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));
        String s = null;
        while ((s = reader.readLine()) != null) {
            String[] str = s.split(" ");
            for (String word : str) {
               fillMap(word);
            }
        }

        print();

        reader.close();
    }
}
