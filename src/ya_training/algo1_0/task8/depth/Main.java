package ya_training.algo1_0.task8.depth;

/*В бинарное дерево поиска добавляются элементы. Выведите глубину для каждого добавленного элемента в том порядке,
как они добавлялись. Если элемент уже есть в дереве, то ничего добавлять и выводить не нужно. Глубиной называется расстояние
от корня дерева до элемента включительно.

Формат ввода
Вводится последовательность целых чисел, оканчивающаяся нулем. Сам ноль в последовательность не входит. По данной последовательности
требуется построить дерево.

Формат вывода
Выведите ответ на задачу.

Пример
Ввод
7 3 2 1 9 5 4 6 8 0
Вывод
1 2 3 4 2 3 4 4 3 */


import java.io.*;
import java.util.*;

public class Main {
    static int firstFree; // первый свободный узел

    static class Node {
        int key;
        int left;
        int right;

        public Node(int key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "{" + "k=" + key + ", l=" + left + ", r=" + right + '}';
        }
    }

    static List<Node> initMemory(int n) {
        List<Node> memory = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            memory.add(new Node(0, i + 1, 0));
        }
        firstFree = 0;
        return memory;
    }

    static int newNode(List<Node> memory) {
        // выделение памяти
        int index = firstFree;
        firstFree = memory.get(index).left;
        return index;
    }

    static void deleteNode(List<Node> memory, int index) {
        // для удаления по индексу, мы заменяем ссылку на следующий текущим первым свободным
        // и индекс становится первый свободный
        memory.get(index).left = firstFree;
        firstFree = index;
    }

    // Бинарное дерево поиска
    // Поиск
    static int find(List<Node> memory, int root, int x) {
        int key = memory.get(root).key;
        if (key == x) {
            return root;
        } else if (key > x) {
            // идем влево
            int left = memory.get(root).left;
            if (left == -1) {
                return -1;
            } else {
                return find(memory, left, x);
            }
        } else {
            // идем вправо
            int right = memory.get(root).right;
            if (right == -1) {
                return -1;
            } else {
                return find(memory, right, x);
            }
        }
    }

    // Добавление элемента и заполнение дерева
    static int createAndFillNode(List<Node> memory, int key) {
        int index = newNode(memory);
        memory.get(index).key = key;
        memory.get(index).left = -1;
        memory.get(index).right = -1;
        return index;
    }

    static void add(List<Node> memory, int root, int x) {
        int key = memory.get(root).key;
        if (key > x) {
            // идем влево
            int left = memory.get(root).left;
            if (left == -1) {
                memory.get(root).left = createAndFillNode(memory, x);
            } else {
                add(memory, left, x);
            }
        } else {
            // идем вправо
            int right = memory.get(root).right;
            if (right == -1) {
                memory.get(root).right = createAndFillNode(memory, x);
            } else {
                add(memory, right, x);
            }
        }
    }

    static int getDepth(List<Node> memory, int root, int x) {
        int key = memory.get(root).key;
        if (key == x) {
            return 0;
        } else if (key > x) {
            // идем влево
            int left = memory.get(root).left;
            if (left == -1) {
                return -1;
            } else {
                return 1 + getDepth(memory, left, x);
            }
        } else {
            // идем вправо
            int right = memory.get(root).right;
            if (right == -1) {
                return -1;
            } else {
                return 1 + getDepth(memory, right, x);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] s = reader.readLine().split(" ");
        int n = s.length;


        List<Node> memory = initMemory(n);
        int root = createAndFillNode(memory, Integer.parseInt(s[0]));
        StringBuilder sb = new StringBuilder("");

        if (memory.get(root).key != 0) { // если возможно сформировать дерево
            sb.append("1").append(" ");
            for (int i = 1; i < n; i++) {
                int num = Integer.parseInt(s[i]);
                if (num == 0)
                    break;
                if (find(memory, root, num) == -1) {
                    add(memory, root, num);
                    sb.append(getDepth(memory, root, num) + 1).append(" ");
                }
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

        reader.close();
    }

}
