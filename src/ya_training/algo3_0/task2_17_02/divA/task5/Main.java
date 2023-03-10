package ya_training.algo3_0.task2_17_02.divA.task5;

/*Петя, которому три года, очень любит играть с машинками. Всего у Пети N различных машинок, которые хранятся на полке шкафа
так высоко, что он сам не может до них дотянуться. Одновременно на полу комнаты может находиться не более K машинок.
Петя играет с одной из машинок на полу и если он хочет поиграть с другой машинкой, которая также находится на полу,
то дотягивается до нее сам. Если же машинка находится на полке, то он обращается за помощью к маме. Мама может достать для
Пети машинку с полки и одновременно с этим поставить на полку любую машинку с пола. Мама очень хорошо знает своего ребенка
и может предугадать последовательность, в которой Петя захочет играть с машинками. При этом, чтобы не мешать Петиной игре,
она хочет совершить как можно меньше операций по подъему машинки с пола, каждый раз правильно выбирая машинку, которую следует
убрать на полку. Ваша задача состоит в том, чтобы определить минимальное количество операций. Перед тем, как Петя начал играть,
все машинки стоят на полке.
Формат ввода
В первой строке содержаться три числа N, K и P (1≤K≤N≤100000,1≤P≤500000). В следующих P строках записаны номера машинок
в том порядке, в котором Петя захочет играть с ними.
Формат вывода
Выведите единственное число: минимальное количество операций, которое надо совершить Петиной маме.
Пример
Ввод	Вывод
3 2 7
1
2
3
1
3
1
2
4
Примечания
Операция 1: снять машинку 1
Операция 2: снять машинку 2
Операция 3: поднять машинку 2 и снять машинку 3
Операция 4: поднять машинку 3 или 1 и снять машинку 2*/

import java.util.*;

public class Main {

    static int n, k, p;
    static int len = 0;
    static int[] heap = new int[100000];
    static int[] idx = new int[100000];
    static List<Integer>[] prior = new ArrayList[100000];
    static int[] seq = new int[500000];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        k = input.nextInt();
        p = input.nextInt();
        for (int i = 0; i < prior.length; i++) {
            prior[i] = new ArrayList<Integer>();
        }
        Arrays.fill(idx, -1);
        for (int i = 0; i < p; i++) {
            seq[i] = input.nextInt() - 1;
            prior[seq[i]].add(i);
        }
        int answer = 0;
        for (int i = 0; i < p; i++) {
            prior[seq[i]].remove(0);
            if (idx[seq[i]] != -1) {
                siftup(idx[seq[i]]);
                continue;
            }
            if (len < k) {
                add(seq[i]);
            } else {
                remove();
                add(seq[i]);
            }
            answer++;
        }
        System.out.println(answer);
    }

    static int parent(int i) {
        return (i - 1) >> 1;
    }

    static int left(int i) {
        return (i << 1) + 1;
    }

    static int right(int i) {
        return (i << 1) + 2;
    }

    static void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        idx[heap[i]] = i;
        idx[heap[j]] = j;
    }

    static boolean greater(int i, int j) {
        if (!prior[heap[i]].isEmpty() && !prior[heap[j]].isEmpty()) {
            return prior[heap[i]].get(0) > prior[heap[j]].get(0);
        } else {
            return prior[heap[i]].isEmpty() && !prior[heap[j]].isEmpty();
        }
    }

    static void siftup(int i) {
        while (i > 0 && greater(i, parent(i))) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    static void siftdown(int i) {
        while (true) {
            int j = i;
            if (left(j) < len && greater(left(j), i)) {
                i = left(j);
            }
            if (right(j) < len && greater(right(j), i)) {
                i = right(j);
            }
            if (i != j) {
                swap(i, j);
            } else {
                break;
            }
        }
    }

    static void add(int item) {
        idx[item] = len;
        heap[len] = item;
        siftup(len++);
    }

    static void remove() {
        swap(0, --len);
        idx[heap[len]] = -1;
        siftdown(0);
    }

}
