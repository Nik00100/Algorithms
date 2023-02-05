package ya_training.algo1_0.task4.maya;

/*Расшифровка письменности Майя оказалась более сложной задачей, чем предполагалось ранними исследованиями.
На протяжении более чем двух сотен лет удалось узнать не так уж много. Основные результаты были получены за последние 30 лет.

Письменность Майя основывается на маленьких рисунках, известных как значки, которые обозначают звуки.
Слова языка Майя обычно записываются с помощью этих значков, которые располагаются рядом друг с другом в некотором порядке.

Одна из проблем расшифровки письменности Майя заключается в определении этого порядка. Рисуя значки некоторого слова,
писатели Майя иногда выбирали позиции для значков, исходя скорее из эстетических взглядов, а не определенных правил.
Это привело к тому, что, хотя звуки для многих значков известны, археологи не всегда уверены, как должно произноситься
записанное слово.

Археологи ищут некоторое слово W. Они знают значки для него, но не знают все возможные способы их расположения.
Поскольку они знают, что Вы приедете на IOI ’06, они просят Вас о помощи. Они дадут Вам g значков, составляющих слово W,
и последовательность S всех значков в надписи, которую они изучают, в порядке их появления. Помогите им, подсчитав количество
возможных появлений слова W.

Задание Напишите программу, которая по значкам слова W и по последовательности S значков надписи подсчитывает количество
всех возможных вхождений слова W в S, то есть количество всех различных позиций идущих подряд g значков в последовательности S,
которые являются какой-либо перестановкой значков слова W .

Формат ввода
1 ≤ g ≤ 3 000, g – количество значков в слове W

g ≤ |S| ≤ 3 000 000 где |S| – количество значков в последовательности S

На вход программы поступают данные в следующем формате:

СТРОКА 1: Содержит два числа, разделенных пробелом – g и |S|. СТРОКА 2: Содержит g последовательных символов, с помощью которых
записывается слово W . Допустимы символы: ‘a’-‘z’ и ‘A’-‘Z’; большие и маленькие буквы считаются различными.
СТРОКА 3: Содержит |S| последовательных символов, которые представляют значки в надписи. Допустимы символы: ‘a’-‘z’ и ‘A’-‘Z’;
большие и маленькие буквы считаются различными.

Формат вывода
Единственная строка выходных данных программы должна содержать количество возможных вхождений слова W в S.

Пример
Ввод
4 11
cAda
AbrAcadAbRa

Вывод
2*/

import java.io.*;
import java.util.*;

public class Main {

    static Map<Character,Integer> makeDict(String str, int length) {
        Map<Character,Integer> answer = new HashMap<>();
        for (int i=0; i<length; i++) {
            answer.put(str.charAt(i), answer.getOrDefault(str.charAt(i),0) + 1);
        }
        //System.out.println(answer.entrySet().stream().map(e->e.getKey()+"-"+e.getValue()).toList());
        return answer;
    }

    static int matchDicts (Map<Character,Integer> sDict, Map<Character,Integer> wDict) {
        int matches = 0;
        for (Character ch : sDict.keySet()) {
            if (wDict.containsKey(ch) && wDict.get(ch) == sDict.get(ch)) {
                matches++;
            }
        }
        return  matches;
    }

    static int modifyDict(Map<Character,Integer> sDict, Map<Character,Integer> wDict, char symbol, int countModifier) {
        int ans = 0;

        if (!sDict.containsKey(symbol)) {
            sDict.put(symbol,0);
        }

        if (wDict.containsKey(symbol) && wDict.get(symbol) == sDict.get(symbol)) {
            ans = -1;
        }
        sDict.put(symbol,sDict.get(symbol) + countModifier);
        // если раньше не совпадало, а теперь стало совпадать, то теперь общее кол-во совпадающих символов увеличилось на 1
        if (wDict.containsKey(symbol) && wDict.get(symbol) == sDict.get(symbol)) {
            ans = 1;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] split =reader.readLine().split(" ");
        int m = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);

        String w = reader.readLine();
        String s = reader.readLine();

        Map<Character,Integer> wDict = makeDict(w,m);
        Map<Character,Integer> sDict = makeDict(s,m);

        int matchingLetters = matchDicts(sDict,wDict);

        int occurrence = 0;
        if (matchingLetters == wDict.size()) {
            occurrence++;
        }

        for (int i=m; i<n; i++) {
            matchingLetters += modifyDict(sDict,wDict,s.charAt(i-m),-1);
            matchingLetters += modifyDict(sDict,wDict,s.charAt(i),1);

            if (matchingLetters == wDict.size()) {
                occurrence++;
            }
        }
        System.out.println(occurrence);
        reader.close();
    }
}
