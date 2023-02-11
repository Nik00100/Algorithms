package ya_training.algo1_0.task8.AVL;

/*Дерево называется АВЛ-сбалансированным, если для любой его вершины высота левого и правого поддерева для этой вершины
различаются не более чем на 1.

Формат ввода
Вводится последовательность целых чисел, оканчивающаяся нулем. Сам ноль в последовательность не входит.
Постройте дерево, соответствующее данной последовательности.

Формат вывода
Определите, является ли дерево сбалансированным, выведите слово YES или NO.

Пример
Ввод	Вывод
7 3 2 1 9 5 4 6 8 0
YES*/

import java.io.*;
import java.util.*;

public class Main {
    static int firstFree; // первый свободный узел
    static  boolean flag = true; // флаг, указывающий на то, что дерево сбалансировано

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
    static int getHeight(List<Node> memory, int root) {
        if (root == -1) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(memory, memory.get(root).left), getHeight(memory, memory.get(root).right));
        }
    }

    static int getMinHeight(List<Node> memory, int root) {
        if (root == -1) {
            return 0;
        } else {
            return 1 + Math.min(getHeight(memory, memory.get(root).left), getHeight(memory, memory.get(root).right));
        }
    }

    static void traverseInOrderAndCheckAVL(List<Node> memory, int root) {
        if (memory.get(root).left != -1)
            traverseInOrderAndCheckAVL(memory, memory.get(root).left);
        if (getHeight(memory,root) - getMinHeight(memory,root) > 1)
            flag = false; // если различаются более чем на 1, то не АВЛ дерево
        if (memory.get(root).right != -1)
            traverseInOrderAndCheckAVL(memory, memory.get(root).right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] s = reader.readLine().split(" ");
        int n = s.length;


        List<Node> memory = initMemory(n);
        int root = createAndFillNode(memory, Integer.parseInt(s[0]));

        if (memory.get(root).key != 0) { // если возможно сформировать дерево
            for (int i = 1; i < n; i++) {
                int num = Integer.parseInt(s[i]);
                if (num == 0)
                    break;
                if (find(memory, root, num) == -1) {
                    add(memory, root, num);
                }
            }
        }
        traverseInOrderAndCheckAVL(memory,root);
        if (flag)
            System.out.println("YES");
        else
            System.out.println("NO");

        reader.close();
    }
}
