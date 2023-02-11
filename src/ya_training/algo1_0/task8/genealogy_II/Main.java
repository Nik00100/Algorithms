package ya_training.algo1_0.task8.genealogy_II;

/*В генеалогическом древе у каждого человека, кроме родоначальника, есть ровно один родитель.

Для каждого элемента дерева определите число всех его потомков (не считая его самого).

Формат ввода
Программа получает на вход число элементов в генеалогическом древе N. Далее следует N−1 строка, задающие родителя для
каждого элемента древа, кроме родоначальника. Каждая строка имеет вид имя_потомка имя_родителя.

Формат вывода
Выведите список всех элементов в лексикографическом порядке, для каждого элемента выводите количество всех его потомков.

Пример
Ввод
9
Alexei Peter_I
Anna Peter_I
Elizabeth Peter_I
Peter_II Alexei
Peter_III Anna
Paul_I Peter_III
Alexander_I Paul_I
Nicholaus_I Paul_I

Вывод
Alexander_I 0
Alexei 1
Anna 4
Elizabeth 0
Nicholaus_I 0
Paul_I 2
Peter_I 8
Peter_II 0
Peter_III 3*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Tree {
        Node root;
        Map<String,String> dic = new HashMap<>(); // потомок - предок
        Map<String, List<String>> parentChildren = new HashMap<>();
        Map<String, Integer> ans = new HashMap<>();

        void findAnswer() {
            traversal(findRoot());
        }
        int traversal(String parent) {
            int count = 0;
            if (parentChildren.containsKey(parent)) {
                count += parentChildren.get(parent).size();
                for (String child : parentChildren.get(parent))
                    count += traversal(child);
            }
            ans.put(parent,count);
            return count;
        }


        void addNodes (Node node) {
            String parent = node.name;
            if (parentChildren.containsKey(parent)) {
                for (String childName : parentChildren.get(parent)) {
                    Node child = new Node(childName);
                    node.children.add(child);
                    child.parent = node;
                    addNodes(child);
                }
            }
        }

        String findRoot() {
            Set<String> children = dic.keySet();
            List<String> parents = new ArrayList<>(dic.values());
            for (String name : parents) {
                if (!children.contains(name)) {
                    return name;
                }
            }
            return null;
        }

        void fillDictionary(String childName, String parentName) {
            dic.put(childName, parentName);
            List<String> children = parentChildren.getOrDefault(parentName, new ArrayList<>());
            children.add(childName);
            parentChildren.put(parentName, children);
        }


        class Node {
            String name;
            Node parent;
            List<Node> children;
            int descendants;

            public Node(String name) {
                this.name = name;
                this.parent = null;
                this.children = new ArrayList<>();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        Tree tree = new Tree();

        for (int i=0; i<n-1; i++) {
            String[] s = reader.readLine().split(" ");
            String child = s[0];
            String parent = s[1];

            tree.fillDictionary(child, parent);
        }

        tree.findAnswer();

        tree.ans.entrySet().stream().sorted((a,b) -> a.getKey().compareTo(b.getKey()))
                .map(entry-> entry.getKey() + " " + entry.getValue()).forEach(System.out::println);

        reader.close();
    }
}
