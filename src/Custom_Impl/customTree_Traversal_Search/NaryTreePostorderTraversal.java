package Custom_Impl.customTree_Traversal_Search;

/*590. N-ary Tree Postorder Traversal  https://leetcode.com/problems/n-ary-tree-postorder-traversal/

Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
Nary-Tree input serialization is represented in their level order traversal.
Each group of children is separated by the null value (See examples)

Example 1:
                    1
                /   |   \
               3    2    4
             /  \
            5    6
Input: root = [1,null,3,2,4,null,5,6]
Output: [5,6,3,2,4,1]*/


import java.util.*;

public class NaryTreePostorderTraversal {

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }



    class SolutionIterative {
        List<Integer> res=new ArrayList<>();
        public List<Integer> postorder(Node root) {
            if(root==null) return res;
            Stack<Node> sk=new Stack<>();
            sk.add(root);
            while(!sk.empty()){
                root = sk.pop();
                res.add(root.val);
                for(Node node: root.children)
                    sk.add(node);
            }
            // System.out.println(res);
            Collections.reverse(res);
            return res;
        }
    }

    class SolutionRecursive {
        List<Integer> res=new ArrayList<>();
        public List<Integer> postorder(Node root) {
            if(root==null) return res;
            for(Node node: root.children) postorder(node);
            res.add(root.val);
            return res;
        }
    }
}
