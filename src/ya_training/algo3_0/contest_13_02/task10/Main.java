package ya_training.algo3_0.contest_13_02.task10;

/*Лёша сидел на лекции. Ему было невероятно скучно. Голос лектора казался таким далеким и незаметным...

Чтобы окончательно не уснуть, он взял листок и написал на нём свое любимое слово. Чуть ниже он повторил своё любимое слово,
без первой буквы. Ещё ниже он снова написал своё любимое слово, но в этот раз без двух первых и последней буквы.

Тут ему пришла в голову мысль — времени до конца лекции все равно ещё очень много, почему бы не продолжить выписывать всеми
возможными способами это слово без какой-то части с начала и какой-то части с конца?

После лекции Лёша рассказал Максу, как замечательно он скоротал время. Максу стало интересно посчитать, сколько букв каждого
вида встречается у Лёши в листочке. Но к сожалению, сам листочек куда-то запропастился.

Макс хорошо знает любимое слово Лёши, а ещё у него не так много свободного времени, как у его друга, так что помогите ему быстро
восстановить, сколько раз Лёше пришлось выписать каждую букву.

Формат ввода
На вход подаётся строка, состоящая из строчных латинских букв — любимое слово Лёши.

Длина строки лежит в пределах от 5 до 100000 символов.

Формат вывода
Для каждой буквы на листочке Лёши, выведите её, а затем через двоеточие и пробел сколько раз она встретилась в выписанных
Лёшей словах (см. формат вывода в примерах). Буквы должны следовать в алфавитном порядке. Буквы, не встречающиеся на листочке,
выводить не нужно.

Пример 1
Ввод
hello

Вывод
e: 8
h: 5
l: 17
o: 5
Пример 2
Ввод
abacaba

Вывод
a: 44
b: 24
c: 16
Примечания
Пояснение к первому примеру. Если любимое Лёшино слово — "hello", то на листочке у Лёши будут выписаны следующие слова:

"hello"

"hell"

"ello"

"hel"

"ell"

"llo"

"he"

"el"

"ll"

"lo"

"h"

"e"

"l"

"l"

"o"

Среди этих слов 8 раз встречается буква "e", 5 раз — буква "h", 17 раз — буква "l" и 5 раз буква "o".*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s = reader.readLine();
        int n = s.length();

        Set<Character> set = new HashSet<>();
        Map<Character, Long> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            set.add(ch);
            long repetitions = (long )(n - i) * (i + 1); // repetition quantity of current char in ith position
            map.put(ch, map.getOrDefault(ch, 0L) + repetitions);
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : set.stream().sorted(Character::compareTo).collect(Collectors.toList())) {
            long quant = map.get(ch);
            sb.append(ch).append(": ").append(quant);
            System.out.println(sb.toString());
            sb.delete(0, sb.length());
        }

        reader.close();
    }
}
