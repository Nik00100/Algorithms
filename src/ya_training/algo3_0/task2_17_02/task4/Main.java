package ya_training.algo3_0.task2_17_02.task4;

/*В этой задаче вам необходимо самостоятельно (не используя соответствующие классы и функции стандартной библиотеки)
организовать структуру данных Heap для хранения целых чисел, над которой определены следующие операции:
a) Insert(k) – добавить в Heap число k ; b) Extract достать из Heap наибольшее число (удалив его при этом).

Формат ввода
В первой строке содержится количество команд N (1 ≤ N ≤ 100000), далее следуют N команд, каждая в своей строке.
Команда может иметь формат: “0 <число>” или “1”, обозначающий, соответственно, операции Insert(<число>) и Extract.
Гарантируется, что при выполенении команды Extract в структуре находится по крайней мере один элемент.

Формат вывода
Для каждой команды извлечения необходимо отдельной строкой вывести число, полученное при выполнении команды Extract.

Пример 1
Ввод
2
0 10000
1

Вывод
10000
Пример 2
Ввод
14
0 1
0 345
1
0 4346
1
0 2435
1
0 235
0 5
0 365
1
1
1
1

Вывод
345
4346
2435
365
235
5
1*/

import java.io.*;
import java.util.*;

public class Main {

    static class MaxHeap<T extends Comparable<T>> {
        private ArrayList<T> items = new ArrayList<T>();

        public void insert(T item) {
            items.add(item);
            siftUp();
        }

        private void siftUp() {
            int k = items.size() - 1;

            while(k > 0) {
                int p = (k - 1) / 2;

                T child = items.get(k);
                T parent = items.get(p);

                if (child.compareTo(parent) > 0) {
                    //swap
                    items.set(k, parent);
                    items.set(p, child);

                    //update k
                    k = p;
                } else {
                    break;
                }
            }

        }

        private void siftDown() {
            int k = 0;
            int left = 1;

            while(left < items.size()) {
                int max = left;
                int right = left + 1;
                if(right < items.size()) {
                    if(items.get(right).compareTo(items.get(left)) > 0) {
                        max = right;
                    }
                }
                T parent = items.get(k);
                T child = items.get(max);

                if(parent.compareTo(child) < 0) {
                    //swap
                    items.set(k, child);
                    items.set(max, parent);

                    k = max;
                    left = 2*k+1;
                }
                else {
                    break;
                }
            }
        }

        public T remove() throws NoSuchElementException {
            if(items.size() == 0) {
                throw new NoSuchElementException();
            }
            else if (items.size() == 1) {
                return items.remove(0);
            }

            T tmp = items.get(0);
            items.set(0, items.remove(items.size()-1));
            siftDown();
            return tmp;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));
        int n = Integer.parseInt(reader.readLine());
        MaxHeap<Integer> heap = new MaxHeap<>();
        for (int i = 0; i < n; i++) {
            String[] s = reader.readLine().split(" ");
            int command = Integer.parseInt(s[0]);

            if (command == 0) {
                int value = Integer.parseInt(s[1]);
                heap.insert(value);
            } else {
                int max = heap.remove();
                System.out.println(max);
            }
        }
        reader.close();
    }
}
