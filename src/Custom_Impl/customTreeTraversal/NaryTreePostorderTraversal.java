package Custom_Impl.customTreeTraversal;

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

    ;

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
