package ya_training.algo3_0.task2_17_02.task2;

/*В игре в пьяницу карточная колода раздается поровну двум игрокам. Далее они вскрывают по одной верхней карте, и тот,
чья карта старше, забирает себе обе вскрытые карты, которые кладутся под низ его колоды. Тот, кто остается без карт – проигрывает.
Для простоты будем считать, что все карты различны по номиналу, а также, что самая младшая карта побеждает самую старшую карту
("шестерка берет туза"). Игрок, который забирает себе карты, сначала кладет под низ своей колоды карту первого игрока,
затем карту второго игрока (то есть карта второго игрока оказывается внизу колоды). Напишите программу, которая моделирует
игру в пьяницу и определяет, кто выигрывает. В игре участвует 10 карт, имеющих значения от 0 до 9, большая карта побеждает меньшую,
карта со значением 0 побеждает карту 9.

Формат ввода
Программа получает на вход две строки: первая строка содержит 5 чисел, разделенных пробелами — номера карт первого игрока,
вторая – аналогично 5 карт второго игрока. Карты перечислены сверху вниз, то есть каждая строка начинается с той карты,
которая будет открыта первой.

Формат вывода
Программа должна определить, кто выигрывает при данной раздаче, и вывести слово first или second, после чего вывести
количество ходов, сделанных до выигрыша. Если на протяжении 106 ходов игра не заканчивается, программа должна вывести слово botva.

Пример 1
Ввод
1 3 5 7 9
2 4 6 8 0
Вывод
second 5

Пример 2
Ввод	Вывод
2 4 6 8 0
1 3 5 7 9

first 5
Пример 3
Ввод	Вывод
1 7 3 9 4
5 8 0 2 6
second 23*/

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] sA = reader.readLine().trim().split(" ");
        String[] sB = reader.readLine().trim().split(" ");
        int n = sA.length;

        Deque<Integer> A = new ArrayDeque<>();
        Deque<Integer> B = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            A.addLast(Integer.parseInt(sA[i]));
            B.addLast(Integer.parseInt(sB[i]));
        }

        int count = 0;
        for (int i = 0; i < 1000_000; i++) {
            if (A.isEmpty() || B.isEmpty() || A.size() > 2*n || B.size() > 2*n) {
                count = i;
                break;
            }

            int numA = A.pollFirst();
            int numB = B.pollFirst();

            if (numA < numB) {
                if (numA == 0 && numB == 9) {
                    A.addLast(numA);
                    A.addLast(numB);
                } else {
                    B.addLast(numA);
                    B.addLast(numB);
                }
            }
            if (numA > numB){
                if (numA == 9 && numB == 0) {
                    B.addLast(numA);
                    B.addLast(numB);
                } else {
                    A.addLast(numA);
                    A.addLast(numB);
                }
            }
        }

        if (!A.isEmpty() && !B.isEmpty()) {
            System.out.println("botva");
        } else if (A.isEmpty()) {
            System.out.println("second " + count);
        } else {
            System.out.println("first " + count);
        }

        reader.close();
    }
}
