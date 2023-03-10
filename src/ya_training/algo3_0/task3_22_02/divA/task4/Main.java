package ya_training.algo3_0.task3_22_02.divA.task4;

/*Папа Карло сменил работу: теперь он работает в мастерской, и целый рабочий день занимается тем, что забивает гвоздики.
Чтобы ему было не скучно, у него в мастерской стоит постоянно работающий телевизор. К сожалению, производительность папы
Карло напрямую зависит от его настроения, а оно, в свою очередь, — от того, что в данный момент показывают по телевизору.
Правда, пока папа Карло забивает гвоздик, он не обращает ни малейшего внимания на телевизор, и поэтому скорость его работы
зависит только от того, что показывали по телевизору в тот момент, когда он только начал забивать этот гвоздик.
Забив очередной гвоздик, он обязательно мельком смотрит в телевизор (его настроение, естественно, меняется), и после этого
может либо сразу начать забивать следующий гвоздик, либо отдохнуть несколько секунд или даже минут за просмотром телевизора.
Папа Карло начинает работу ровно в 9 часов. С 13 часов у него начинается обеденный перерыв. При этом если он незадолго до
обеда хочет начать вбивать гвоздик, но понимает, что до перерыва он не закончит эту работу, то он и не начинает ее. Аналогично
в 14 часов он вновь приступает к работе, а в 18 уходит домой. Это значит, что в 9:00:00 (аналогично, как и в 14:00:00) он
уже может начать забивать гвоздик. Если он хочет начать вбивать гвоздик в 12:59:59 или 17:59:59 и на это у него уйдет 1 секунда,
то он успевает вбить гвоздик до обеда или до окончания работы соответственно, а если 2 секунды, — то уже нет.
Известна программа телевизионных передач и то, как они влияют на папу Карло. Требуется составить график работы и маленьких
перерывчиков папы Карло так, чтобы за рабочий день он вбил максимально возможное количество гвоздей.

Формат ввода
Во входном файле записано расписание телевизионных передач с 9:00:00 до 18:00:00 в следующем формате. В первой строке число
N — количество телевизионных передач в этот период (1≤N≤32400). В каждой из последующих N строк записано описание одной передачи:
сначала время ее начала в формате ЧЧ:ММ:СС (ЧЧ — две цифры, задающие часы, ММ — две цифры, задающие минуты начала, СС — две цифры,
 задающие секунды начала). А затем через один или несколько пробелов число Ti — время в секундах, которое папа Карло будет
 тратить на забивание одного гвоздика, если он перед этим увидит по телевизору эту передачу (1≤Ti≤32400).
Передачи записаны в хронологическом порядке. Первая передача всегда начинается в 09:00:00. Можно считать, что последняя передача
заканчивается в 18:00:00.

Формат вывода
В первую строку выходного файла требуется вывести максимальное количество гвоздиков, которое папа Карло успеет вбить за рабочий день.
Пример 1
Ввод	Вывод
2
09:00:00 3600
14:00:00 3600
8
Пример 2
Ввод	Вывод
4
09:00:00 1800
12:59:31 10
13:45:23 1800
15:00:00 3600
14
Примечания
В первом примере каждый час папа Карло вбивает по одному гвоздику
Во втором примере первую половину дня он вбивает по гвоздику за полчаса, но в 12:30:00 он не начинает вбивать гвоздики,
а ждет 12:59:31 и успевает до обеда вбить 2 гвоздика.
С 14 до 15 часов вбиваются 2 гвоздя, а затем по одному гвоздю в час.*/

import java.util.*;
import java.io.*;

public class Main {
    static final int BEGIN = getSecond("09:00:00");
    static final int FINISH = getSecond("18:00:00");
    static final int START_DINNER = getSecond("13:00:00");
    static final int END_DINNER = getSecond("14:00:00");


    static int getSecond(String time) {
        String[] s = time.split(":");
        int hour = Integer.parseInt(s[0]);
        int minute = Integer.parseInt(s[1]);
        int second = Integer.parseInt(s[2]);
        return hour*3600 + minute*60 + second;
    }

    static void fillTime(int begin, int workSpeed, int[] timeBeforeLunch, int[] timeAfterLunch) {
        if (begin < START_DINNER) {
            Arrays.fill(timeBeforeLunch, begin - BEGIN, timeBeforeLunch.length, workSpeed);
            Arrays.fill(timeAfterLunch, 0, timeAfterLunch.length, workSpeed);
        } else if (begin>= START_DINNER && begin <= END_DINNER) {
            Arrays.fill(timeAfterLunch, 0, timeAfterLunch.length, workSpeed);
        } else {
            Arrays.fill(timeAfterLunch, begin - END_DINNER, timeAfterLunch.length, workSpeed);
        }
    }

    static int count(int[] dp, int[] time) {
        Arrays.fill(dp, 0);
        int n = time.length;
        for (int i = 0; i < n; i++) {
            if (i > 0) dp[i] = Math.max(dp[i - 1], dp[i]);
            if (i + time[i] <= n) dp[i+time[i]] = Math.max(dp[i + time[i]], dp[i] + 1);
        }
        return Math.max(dp[n], dp[n - 1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        int n = Integer.parseInt(reader.readLine());

        int N1 = START_DINNER - BEGIN;
        int N2 = FINISH - END_DINNER;
        int[] timeBeforeLunch = new int[N1];
        int[] timeAfterLunch = new int[N2];

        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int begin = getSecond(s[0].trim());
            int workSpeed = Integer.parseInt(s[1].trim());
            fillTime(begin, workSpeed, timeBeforeLunch, timeAfterLunch);
        }

        System.out.println(count(new int[N1 + 1], timeBeforeLunch) + count(new int[N2 + 1], timeAfterLunch));

        reader.close();
    }
}
