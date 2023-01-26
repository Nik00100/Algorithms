package Custom_Impl.Custom_Tree_Traversal_Search;



import java.util.*;

public class BinaryTreePostorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            postorder(root, ans);
            return ans;
        }

        private void postorder(TreeNode root, List<Integer> ans) {
            if (root == null)
                return;

            postorder(root.left, ans);
            postorder(root.right, ans);
            ans.add(root.val);
        }
    }
}