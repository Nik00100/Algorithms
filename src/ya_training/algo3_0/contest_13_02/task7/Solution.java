package ya_training.algo3_0.contest_13_02.task7;

/*Для того чтобы компьютеры поддерживали актуальное время, они могут обращаться к серверам точного времени
SNTP (Simple Network Time Protocol). К сожалению, компьютер не может просто получить время у сервера, потому что информация
по сети передаётся не мгновенно: пока сообщение с текущим временем дойдёт до компьютера, оно потеряет свою актуальность.
Протокол взаимодействия клиента (компьютера, запрашивающего точное время) и сервера (компьютера, выдающего точное время)
выглядит следующим образом:

1. Клиент отправляет запрос на сервер и запоминает время отправления A (по клиентскому времени).

2. Сервер получает запрос в момент времени B (по точному серверному времени) и отправляет клиенту сообщение, содержащее время B.

3. Клиент получает ответ на свой запрос в момент времени C (по клиентскому времени) и запоминает его.
Теперь клиент, из предположения, что сетевые задержки при передаче сообщений от клиента серверу и от сервера клиенту одинаковы,
может определить и установить себе точное время, используя известные значения A, B, C.

Вам предстоит реализовать алгоритм, с точностью до секунды определяющий точное время для установки на клиенте по известным A, B и C.
При необходимости округлите результат до целого числа секунд по правилам арифметики (в меньшую сторону, если дробная часть числа
меньше 1/2, иначе в большую сторону).

Возможно, что, пока клиент ожидал ответа, по клиентскому времени успели наступить новые сутки, однако известно, что между отправкой
клиентом запроса и получением ответа от сервера прошло менее 24 часов.

Формат ввода
Программа получает на вход три временные метки A, B, C, по одной в каждой строке. Все временные метки представлены в формате
«hh:mm:ss», где «hh» – это часы, «mm» – минуты, «ss» – секунды. Часы, минуты и секунды записываются ровно двумя цифрами каждое
(возможно, с дополнительными нулями в начале числа).

Формат вывода
Программа должна вывести одну временную метку в формате, описанном во входных данных, – вычисленное точное время для установки на
клиенте. В выводе не должно быть пробелов, пустых строк в начале вывода.

Пример
Ввод
15:01:00
18:09:45
15:01:40

Вывод
18:10:05*/

import java.io.*;

public class Solution {
    static void calcTime(int timeInSeconds, boolean flagOdd) {
        if (flagOdd) timeInSeconds++;
        int hour = timeInSeconds / 3600;
        timeInSeconds %= 3600;
        int minute = timeInSeconds / 60;
        int second =  timeInSeconds % 60;
        System.out.println(getTimeInFormat(hour,minute,second));
    }

    static int getSecond(String time) {
        String[] s = time.split(":");
        int hour = Integer.parseInt(s[0]);
        int minute = Integer.parseInt(s[1]);
        int second = Integer.parseInt(s[2]);
        return hour*3600 + minute*60 + second;
    }

    static String getTimeInFormat (int hour, int minute, int second) {
        StringBuilder sb = new StringBuilder();
        if (hour/10 == 0) {
            sb.append(0).append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }

        if (minute/10 == 0) {
            sb.append(0).append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }

        if (second/10 == 0) {
            sb.append(0).append(second);
        } else {
            sb.append(second);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        int A = getSecond(reader.readLine());
        int B = getSecond(reader.readLine());
        int C = getSecond(reader.readLine());

        int fullDay = 24*3600;
        boolean flagOdd = (C - A) % 2 != 0;

        int timeInSeconds;
        if (C >= A) {
            timeInSeconds = B + (C - A) / 2;
            if (timeInSeconds <= fullDay) {
                calcTime(timeInSeconds, flagOdd);
            } else {
                timeInSeconds = ((C - A) / 2) - (fullDay - B);
                calcTime(timeInSeconds, flagOdd);
            }
        } else {
            timeInSeconds = B + (C + fullDay - A) / 2;
            if (timeInSeconds <= fullDay) {
                calcTime(timeInSeconds, flagOdd);
            } else {
                timeInSeconds = ((C + fullDay - A) / 2) - (fullDay - B);
                calcTime(timeInSeconds, flagOdd);
            }
        }

        reader.close();
    }
}
