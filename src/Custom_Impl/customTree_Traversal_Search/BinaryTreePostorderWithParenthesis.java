package Custom_Impl.customTree_Traversal_Search;

/*606. Construct String from Binary Tree  https://leetcode.com/problems/construct-string-from-binary-tree/description/

Given the root of a binary tree, construct a string consisting of parenthesis and integers from a binary tree with
the preorder traversal way, and return it.
Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship between the string and
the original binary tree.

Example 1:
                1
               / \
              2   3
             /
            4
Input: root = [1,2,3,4]
Output: "1(2(4))(3)"
Explanation: Originally, it needs to be "1(2(4)())(3()())", but you need to omit all the unnecessary
empty parenthesis pairs. And it will be "1(2(4))(3)"

*/

import java.util.*;

public class BinaryTreePostorderWithParenthesis {
    static public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    static class Solution {
        public String tree2str(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            dfs(root,sb);
            return sb.toString();
        }

        public void dfs (TreeNode node, StringBuilder sb) {
            if (node == null) return;
            sb.append(node.val);
            if (node.left == null && node.right == null) return;
            if (node.left != null) {
                sb.append('(');
                dfs(node.left, sb);
                sb.append(')');
            }
            if (node.right != null) {
                sb.append('(');
                dfs(node.right, sb);
                sb.append(')');
            }
        }


        public String tree2strIterative(TreeNode t) {
            if (t == null)
                return "";
            Stack < TreeNode > stack = new Stack < > ();
            stack.push(t);
            Set < TreeNode > visited = new HashSet< >();
            StringBuilder s = new StringBuilder();
            while (!stack.isEmpty()) {
                t = stack.peek();
                if (visited.contains(t)) {
                    stack.pop();
                    s.append(")");
                } else {
                    visited.add(t);
                    s.append("(" + t.val);
                    if (t.left == null && t.right != null)
                        s.append("()");
                    if (t.right != null)
                        stack.push(t.right);
                    if (t.left != null)
                        stack.push(t.left);
                }
            }
            return s.substring(1, s.length() - 1);
        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2),new TreeNode(3));
        root.left.left = new TreeNode(4);
        System.out.println(new Solution().tree2str(root));
    }
}
