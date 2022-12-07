package task6MaxXorInArray;

import java.io.*;
import java.util.*;

public class Solution {
    // Идея состоит в использовании бинарного префиксного дерева, где у каждого узла может быть только 2 потомка
    // один потомок представляет бит "1" в бинарном представлении числа, другой потомок представляет бит "0"
    private static class TrieNode {
        // children[0]: представляет бит "1",  children[1]: представляет бит "0"
        private final TrieNode[] children;
        private TrieNode() {
            children = new TrieNode[2];
        }
    }

    // строим префиксное двоичное дерево
    private static void buildTrie(List<Integer> nums, TrieNode root) {
        TrieNode curr;
        for (int num : nums) {
            // начинаем с корневого узла
            curr = root;
            // поскольку все числа в массиве положительные, то 31й бит всегда 0, и мы его игнорируем, начиная с 30ого
            for (int i = 30; i >= 0; --i) {
                int bit = getIthBit(num, i);
                // если текущий бит 0, то устанавливаем потомков в children[1], иначе в children[0]
                if (curr.children[bit ^ 1] == null) {
                    curr.children[bit ^ 1] = new TrieNode();
                }
                curr = curr.children[bit ^ 1];
            }
        }
    }

    // вычисляем max XOR для пар чисел в массиве
    private static int maximumXOR(List<Integer> nums, TrieNode root) {
        TrieNode curr;
        int ans = Integer.MIN_VALUE;
        // перебираем все числа массива, и перебираем биты числа, начиная с самого правого бита числа,
        // двигаемся к узлу, который представляет XOR текущего бита
        for (int num : nums) {
            // начинаем с корневого узла
            curr = root;
            // сохраняем локальный результат максимального XOR
            int rst = 0;
            for (int i = 30; i >= 0; --i) {
                int bit = getIthBit(num, i);
                // если bit=1, двигаемся к children[1] (представляет 0)
                // если bit=0, двигаемся к children[0] (представляет 1)
                if (curr.children[bit] != null) {
                    curr = curr.children[bit];
                    // если узел найден, то мы получили "1" для текущего индекса
                    // и можем обновить локальный результат максимального XOR
                    rst += (1 << i);
                }
                // если узел не найден
                else {
                    curr = curr.children[bit ^ 1];
                }
            }
            // обновляем общий максимум
            ans = Math.max(ans, rst);
            // возможен вариант, когда достигнуто максимальное значение, которое вмещает в себя тип int
            if (ans == Integer.MAX_VALUE) break;
        }
        return ans;
    }

    // i-й бит числа num
    private static int getIthBit(int num, int i) {
        return (num & (1 << i)) == 0 ? 0 : 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Integer> nums = new ArrayList<>();
        // корневой узел
        TrieNode root = new TrieNode();

        for (int i=0; i<n; i++) {
            nums.add(Integer.parseInt(reader.readLine()));
            buildTrie(nums,root);
            System.out.println(maximumXOR(nums,root));
        }
    }

}
