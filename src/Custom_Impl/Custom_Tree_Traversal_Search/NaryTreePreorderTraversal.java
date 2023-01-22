package Custom_Impl.Custom_Tree_Traversal_Search;

/*589. N-ary Tree Preorder Traversal

Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
Nary-Tree input serialization is represented in their level order traversal.
Each group of children is separated by the null value (See examples)

Example 1:
                    1
                /   |   \
               3    2    4
              / \
             6   5
Input: root = [1,null,3,2,4,null,5,6]
Output: [1,3,5,6,2,4]*/

import java.util.*;

public class NaryTreePreorderTraversal {
    class Node {
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
    }

    public List<Integer> preorder(Node root) {
        // To store the output array...
        List<Integer> output = new ArrayList<Integer>();
        // Base case: if the tree is empty...
        if (root == null) return output;
        // Create a stack of Node and push root to it...
        Stack<Node> st = new Stack<>();
        st.push(root);
        // Traverse till stack is not empty...
        while (!st.isEmpty()) {
            // Pop a Node from the stack and add it to the output list...
            Node node = st.pop();
            output.add(node.val);
            // Push all of the child nodes of the node into the stack from right to left...
            // Push from right to left to get the right preorder traversal...
            for (int idx = node.children.size() - 1; idx >= 0; idx--) {
                Node child = node.children.get(idx);
                st.push(child);
            }
        }
        return output;      // Return the output...

    }
}
