package ya_training.algo1_0.task3.cubics;

/*Аня и Боря любят играть в разноцветные кубики, причем у каждого из них свой набор и в каждом наборе все кубики различны по цвету. Однажды дети заинтересовались, сколько существуют цветов таких, что кубики каждого цвета присутствуют в обоих наборах. Для этого они занумеровали все цвета случайными числами. На этом их энтузиазм иссяк, поэтому вам предлагается помочь им в оставшейся части. Номер любого цвета — это целое число в пределах от 0 до 109.

Формат ввода
В первой строке входного файла записаны числа N и M — количество кубиков у Ани и Бори соответственно. В следующих N строках заданы номера цветов кубиков Ани. В последних M строках номера цветов кубиков Бори.

Формат вывода
Выведите сначала количество, а затем отсортированные по возрастанию номера цветов таких, что кубики каждого цвета есть в обоих наборах, затем количество и отсортированные по возрастанию номера остальных цветов у Ани, потом количество и отсортированные по возрастанию номера остальных цветов у Бори.

Пример 1
Ввод
4 3
0
1
10
9
1
3
0
Вывод
2
0 1
2
9 10
1
3
Пример 2
Ввод
2 2
1
2
2
3
Вывод
1
2
1
1
1
3
Пример 3
Ввод
0 0
Вывод
0
0
0*/
import java.io.*;
import java.util.*;

public class Main {

    static void print (List<Integer> list) {
        if (list.size() != 0) {
            System.out.println(list.size());
            StringBuilder sb = new StringBuilder();
            for (Integer num : list) {
                sb.append(num).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
        } else {
            System.out.println(0);
            System.out.println();
        }
    }

    // тоже самое, что List<Integer> common = setN.stream().filter(num->setM.contains(num)).sorted().toList();
    // для валидатора
    static List<Integer> getCommon (Set<Integer> s1, Set<Integer> s2) {
        List<Integer> ans = new ArrayList<>();
        for (Integer num : s1) {
            if (s2.contains(num)) ans.add(num);
        }
        if (ans.size() != 0) Collections.sort(ans);
        return ans;
    }

    // тоже самое, что List<Integer> uniqueSetN = setN.stream().filter(num->!common.contains(num)).sorted().toList();
    // для валидатора
    static List<Integer> getUnique (List<Integer> list, Set<Integer> s) {
        List<Integer> ans = new ArrayList<>();
        for (Integer num : s) {
            if (!list.contains(num)) ans.add(num);
        }
        if (ans.size() != 0) Collections.sort(ans);
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Nik Kirillov\\Downloads\\Telegram Desktop\\020.txt")));
        String[] s = reader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        Set<Integer> setN = new HashSet<>();
        Set<Integer> setM = new HashSet<>();

        for (int i = 0; i < n; i++) {
            setN.add(Integer.parseInt(reader.readLine()));
        }

        for (int i = 0; i < m; i++) {
            setM.add(Integer.parseInt(reader.readLine()));
        }

        List<Integer> common = getCommon(setN,setM);
                //setN.stream().filter(num->setM.contains(num)).sorted().toList();
        print(common);

        List<Integer> uniqueSetN = getUnique(common,setN);
                //setN.stream().filter(num->!common.contains(num)).sorted().toList();
        print(uniqueSetN);

        List<Integer> uniqueSetM = getUnique(common,setM);
                //setM.stream().filter(num->!common.contains(num)).sorted().toList();
        print(uniqueSetM);

        reader.close();
    }
}
