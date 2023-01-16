package Custom_Impl.customBinaryTreeLevelTraversal;

/*Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal,
each group of children is separated by the null value (See examples).

Example 1:
                1
              / | \
            3   2   4
          /   \
          5    6
Input: root = [1,null,3,2,4,null,5,6]
Output: [[1],[3,2,4],[5,6]]*/

import java.util.*;

public class Main {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };


    static class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> ans = new ArrayList<>();
            if (root == null) return ans;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                int rowLength = queue.size();
                List<Integer> rowList = new ArrayList<>();
                for (int i=0; i< rowLength; i++) {
                    Node node = queue.poll();
                    rowList.add(node.val);
                    if (node.children == null) continue;
                    int childrenSize = node.children.size();
                    for (int j=0; j<childrenSize; j++) {
                        if(node.children.get(j)!=null) queue.offer(node.children.get(j));
                    }
                }
                ans.add(rowList);
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1,new ArrayList<>());
        Node second = new Node(2);
        Node third = new Node(3,new ArrayList<>());
        Node forth = new Node(4);
        Node fifth = new Node(5);
        Node sixth = new Node(6);
        List<Node> rootChild = root.children;
        rootChild.add(second);
        rootChild.add(third);
        rootChild.add(forth);
        List<Node> thirdChild = third.children;
        thirdChild.add(fifth);
        thirdChild.add(sixth);
        System.out.println(new Solution().levelOrder(root));
    }
}
