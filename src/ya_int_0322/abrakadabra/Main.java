package ya_int_0322.abrakadabra;

/*Недавно Кузя разбирал чердак на даче своей бабушки и нашел очень древнюю и непонятную книгу. Кузя сразу понял, что это книга
с заклинаниями, а бабушка — волшебница, просто это скрывает. А раз бабушка умеет творить магию, то и Кузя сможет!
Кузя решил тут же применить свои недюжинные навыки чтения и прочитать какие-нибудь заклинания из книги.
В дальнейшем будем считать, что все записи в книге представляют собой одну большую строку S. Все символы в книге представляют
собой малые латинские буквы. Кузя смотрел много фильмов про волшебников, поэтому знает два важных правила:

    - Если в данный момент прочитан символ на позиции i, то следующим Кузя должен прочитать символ на позиции pi;
    -Пусть ki — порядковый номер в алфавите символа на i-й позиции в тексте (a — 0-й, z — 25-й).
    Если Кузя за время одного заклинания должен прочитать символ на i-й позиции в mi-й раз, то вместо этого он вслух
    произносит символ с порядковым номером в алфавите (ki+(mi−1)⋅di)mod 26.

Подробный пример находится в примечании к тестовым примерам (в самом низу).
Обратите внимание, что изменения символов при прочтении действуют только в рамках одного заклинания (mi считаются независимо
для каждого прочтения заклинания).
Кузя считает, что сила прочитанного заклинания равна количеству уникальных символов, которые в него вошли. К примеру, в заклинании
«zbacbef» ровно 6 уникальных символов [a,b,c,e,f,z].
Кузя нашел на обложке книги число K и понял, что для оптимального эффекта необходимо прочесть заклинания всех длин от
1 до K включительно, начав по очереди с каждого символа от 1 до N (всего Кузя планирует прочесть N×K заклинаний).
Кузя боится слишком мощных выбросов магической энергии. Поэтому он просит вас, как победителя викторины по Гарри Поттеру в
5-м классе, заранее вычислить суммарную силу всех N×K  заклинаний, которые он собирается прочесть.

Формат ввода
В первой строке дано два целых числа N и K(1≤N≤10^5,1≤K≤10^9) — количество символов в тексте книги.
Во второй строке дана строка S длины N, состоящая из малых латинских букв (Si∈[a…z]) — текст книги с заклинаниями.
В третьей строке дано N целых чисел pi через пробел (1≤pi≤N) — позиция символа, который следует прочесть после чтения
символа на i -й позиции.
В четвертой строке дано N целых чисел di через пробел (0≤di≤25) — сдвиг при повторном чтении символа на i-й позиции
(читайте условие и примечание).

Формат вывода
В единственной строке выведите единственное число — суммарную силу всех N×K заклинаний, начинающихся в каждой
из позиций 1,2,…,N и имеющих длину 1,2,…,K.
Пример 1
Ввод
3 7
abz
3 1 2
4 0 3

Вывод
74

Пример 2
Ввод
4 6
abcd
2 3 1 4
1 0 2 13

Вывод
62

Пример 3
Ввод
10 1000000000
qwertzxcvb
2 3 4 5 3 4 8 7 10 2
1 2 3 4 5 6 7 8 9 10

Вывод
259999995297

Примечания
Пояснение к первому тестовому примеру.
Текст в книге равен строке S= «abz»;
Массив p равен [3,1,2];
Массив d равен [4,0,3];
Разберем детально чтение одного из заклинаний: пусть Кузя начнёт читать заклинание с позиции
3 в тексте и прочтёт 7 символов. В таком случае он прочтёт строку «zbacbef»:

1. Символ на 3-й позиции равен z.Кузя читает его в m3=1-й раз, поэтому он читает символ z без изменений. После этого Кузя должен прочесть символ на позиции p3=2;
2. Аналогично Кузя читает символ b на 2-й позиции в m2=1-й раз, поэтому он читает именно символ b и переходит к символу p2=1;
3. Далее Кузя читает символ a на 1-й позиции в m1=1-й раз, поэтому он читает символ a без изменений и переходит к символу p1=3;
4. Кузя читает символ на позиции3, но уже в m3=2-й раз. Из этого следует, что на самом деле Кузя должен произнести не z
(25-й в алфавите), а (25+(2−1)⋅3)mod26=2-й символ в алфавите - это символ c. После чего Кузя снова должен перейти к символу
на позиции p3=2;
5. Так как d2=0, то на 2-й позиции Кузя будет всегда читать один и тот же символ b;
6. А вот вместо символа a (0-й в алфавите) на позиции 1 Кузя в m1=2-й раз прочтёт символ (0+(2−1)⋅4)mod 26=4-й символ
в алфавите — символ e;
7. Завершает Кузя чтением символа на позиции 3 в m3=3-й раз, поэтому в этот раз он прочтёт (25+(3−1)⋅3)mod 26=5-й символ в
алфавите - это символ f.
Список всех заклинаний, которые Кузя прочтёт:

Начиная с позиции 1:
a — сила 1;
az — сила 2;
azb — сила 3;
azbe — сила 4;
azbec — сила 5;
azbecb — сила5 (символ b уже встречался ранее, поэтому не увеличивает силу);
azbecbi — сила 6 (i получился как 4-й символ после e).
Начиная с позиции 2:
b — сила 1;
ba — сила 2;
baz — сила 3;
bazb — сила 3;
bazbe — сила 4;
bazbec — сила 5;
bazbecb — сила 5.
Начиная с позиции 3 (подробно описаны выше):
z — сила 1;
zb — сила 2;
zba — сила 3;
zbac — сила 4;
zbacb — сила 4;
zbacbe — сила 5;
zbacbef — сила 6.
Суммарная сила всех прочтённых заклинаний равна 74.

Пояснение ко второму тестовому примеру.
Список всех заклинаний, которые Кузя прочтёт:
Начиная с позиции 1:
a — сила 1;
ab — сила 2;
abc — сила 3;
abcb — сила 3 (b получился как 1-й символа после a);
abcbb — сила 3;
abcbbe — сила4 (e получился как 2-й символ после c).
Начиная с позиции 2:
b — сила 1;
bc — сила 2;
bca — сила 3;
bcab — сила 3;
bcabe — сила 4;
bcabeb — сила4.
Начиная с позиции 3:
c — сила 1;
ca — сила 2;
cab — сила 3;
cabe — сила 4;
cabeb — сила 4;
cabebb — сила 4.
Начиная с позиции 4:
d — сила 1;
dq — сила 2 (q получился как 13-й символ после d);
dqd — сила2 (d получился как 13-й символ после q);
dqdq — сила 2;
dqdqd — сила 2;
dqdqdq — сила 2.
Суммарная сила всех прочтённых заклинаний равна 62.

Пояснение к третьему тестовому примеру.
В данном тесте мы ограничимся только одним из 10 миллиардов прочтённых Кузей заклинаний.
Если начать с позиции 1 и прочесть 7 символов, то получится заклинание «qwerthv»:

q на позиции 1;
w на позиции 2;
e на позиции 3;
r на позиции4;
t на позиции 5;
h на позиции 3 (во 2-й раз вместо 4-го символа в алфавите «e» Кузя произнесёт 4+3⋅(2−1)=7-й символ);
v на позиции 4 (во 2-й раз вместо 17-го символа в алфавите «r» Кузя произнесёт 17+4⋅(2−1)=21-й символ).
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.nextLine();

        String s = scanner.nextLine();
        int[] p = new int[n];
        int[] d = new int[n];
        StringTokenizer st = new StringTokenizer(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        st = new StringTokenizer(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            d[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            int cur = i;
            int len = 0;
            while (len < k) {
                cnt[s.charAt(cur) - 'a']++;
                len++;
                cur = p[cur];
                if (len % n == 0) {
                    int sum = 0;
                    for (int j = 0; j < 26; j++) {
                        if (cnt[j] > 0) {
                            sum++;
                        }
                    }
                    result += sum;
                }
                if (len >= n && cur == i) {
                    break;
                }
            }
            int sum = 0;
            for (int j = 0; j < 26; j++) {
                if (cnt[j] > 0) {
                    sum++;
                }
            }
            int t = (k - len) / n;
            result += sum * t;
        }

        System.out.println(result);
    }

}