package ya_int_0322.keyboard_done;

/*Кузя снова не успел сдать реферат по языковым разнообразиям в срок. «Наверное, у меня какая-то неоптимальная клавиатура...»
— подумал Кузя и решил изобрести самую оптимальную клавиатуру для набора одним пальцем.
Кузя решил, что его клавиатура будет содержать
N  рядов с клавишами (разные ряды могут содержать разные количества клавиш). Все клавиши на клавиатуре будут уникальными.
Чтобы оценить качество клавиатуры для набора определённого текста, Кузя ввёл понятие «разнорядности»:

Пусть текст представляет из себя строку S длины K;
Назовём переход между соседними в тексте символами Si−1 и Si(2≤i≤K) разнорядным, если данные символы находятся на различных
рядах клавиатуры;
Разнорядность равна количеству разнорядных переходов при наборе текста на заданной клавиатуре.
Например, пусть текст S равен ABCAD, а клавиатура содержит два ряда клавиш AC и BD. В таком случае при наборе текста будет ровно
3 разнорядных перехода: S1 = A в S2=B (ряд 1 в ряд 2);S2= B в S3=C (ряд 2 в ряд 1);S4=A в S5=D (ряд 1 в ряд 2).
Вас, как лучшего игрока в Тетрис среди знакомых, Кузя просит вычислить разнорядность созданной им клавиатуры на последнем
из Кузиных рефератов.

Формат ввода
Первая строка содержит одно целое число
N(1≤N≤2⋅105) — количество клавиш на клавиатуре.
Вторая строка содержит N целых чисел ci(0≤ci≤109) — идентификаторы символов на клавишах. Гарантируется, что все значения ci различны.
Третья строка содержит N целых чисел ri(1≤ri≤109). Число ri задает номер ряда на клавиатуре, в котором расположена клавиша
с символом ci.
Четвертая строка содержит одно целое число K(1≤K≤2⋅105) — количество символов в реферате.
Пятая строка содержит K целых чисел sj(0≤sj≤109) — идентификаторы символов реферата в порядке набора на клавиатуре.
Гарантируется, что для любого sj существует такой i, что sj=ci — любой символ из реферата присутствует на клавиатуре.

Формат вывода
Выведите единственное целое число — разнорядность заданной во входных данных конструкции клавиатуры на реферате S.
Пример 1
Ввод	Вывод
4
1 2 3 4
1 2 1 2
5
1 2 3 1 4
3
Пример 2
Ввод	Вывод
3
42 3 14
1 3 3
4
3 14 14 3
0
Примечания
Пояснение к первому тестовому примеру.
Данный пример соответствует примеру из условия, где каждой букве сопоставлен её номер в латинском алфавите:
«A» —1;
«B» —2;
«C» —3;
«D» —4.
Как и в условии, первый ряд содержит символы 1 и 3, второй — 2 и 4.
Ответ 3 получается аналогично условию:
при переходе от символа 1 к символу 2;
при переходе от символа 2 к символу 3;
при переходе от символа 1 к символу 4.

Пояснение ко второму тестовому примеру.
Первый ряд содержит клавишу с идентификатором 42;
Второй ряд пустует;
Третий ряд — клавиши с идентификаторами 3 и 14
.
Все клавиши, используемые при наборе текста реферата, находятся в 3-м ряду, поэтому разнорядность клавиатуры равна 0.*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = scanner.nextInt();
        }
        int[] rows = new int[n];
        for (int i = 0; i < n; i++) {
            rows[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        int[] text = new int[k];
        for (int i = 0; i < k; i++) {
            text[i] = scanner.nextInt();
        }
        Map<Integer, Integer> rowMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            rowMap.put(ids[i], rows[i]);
        }
        int transitions = 0;
        int currentRow = rowMap.get(text[0]);
        for (int i = 1; i < k; i++) {
            int nextRow = rowMap.get(text[i]);
            if (currentRow != nextRow) {
                transitions++;
            }
            currentRow = nextRow;
        }
        System.out.println(transitions);
    }
}

