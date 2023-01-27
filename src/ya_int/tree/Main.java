package ya_int.tree;

import java.io.*;
import java.util.*;

public class Main {
    static class Tree {
        TreeNode root;

        public Tree(int val) {
            if (val > 0) {
                this.root = new TreeNode(1);
                generateTree(root, val);
            }
        }
        private class TreeNode {
            TreeNode left;
            TreeNode right;
            TreeNode parent;
            int val;
            public TreeNode(int val) {
                this.val = val;
            }
        }

        // build tree
        private void generateTree (TreeNode node, int n) {
            int value = node.val;
            if (value >= n) return;
            if (value*2 <= n) {
                TreeNode left = new TreeNode(value*2);
                left.parent = node;
                node.left = left;
                generateTree(node.left, n);
            }
            if (value*2 + 1 <= n){
                TreeNode right = new TreeNode(value*2 + 1);
                right.parent = node;
                node.right = right;
                generateTree(node.right, n);
            }
        }

        // find and change Node with parent
        private TreeNode findNode (int val) {
            if (root == null) return null;
            if (root.val == val) return root;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.val == val) return node;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.add(node.right);
            }
            return null;
        }

        public void changeNodeWithParent (int val) {
            // for some reason we couldn't find any nodes with value == val
            TreeNode v = findNode(val);
            if (v == null) return;

            // don't change nodes if v root
            TreeNode p = v.parent;
            if (p == null) return;

            // if parent of p == null then it means p is root, so new root become v
            TreeNode pp = p.parent;
            if (pp == null) {
                root = v;
            }

            TreeNode vl = v.left;
            TreeNode vr = v.right;

            // replace parents
            v.parent = pp;
            p.parent = v;

            // replace children
            // if p is left child pp
            if (pp != null && p == pp.left) {
                pp.left = v;
            }
            // if p is right child pp
            if (pp != null && p == pp.right) {
                pp.right = v;
            }

            // if v is left child p
            if (p !=null && v == p.left) {
                v.left = p;
                p.left = vl;
                if (vr !=null) vl.parent = p;
            }
            // if v is right child p
            if (p != null && v == p.right) {
                v.right = p;
                p.right = vr;
                if (vr !=null) vr.parent = p;
            }
        }


        // inorder traversal
        public List<Integer> traversal() {
            List<Integer> res = new ArrayList<>();
            helper(root, res);
            return res;
        }

        private void helper(TreeNode node, List<Integer> res) {
            if (node != null) {
                helper(node.left, res);
                res.add(node.val);
                helper(node.right, res);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] s = reader.readLine().split(" ");
        String[] str = reader.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int count = Integer.parseInt(s[1]);
        List<Integer> nodes = new ArrayList<>();
        for (int i=0; i<count; i++) {
            nodes.add(Integer.parseInt(str[i]));
        }

        Tree tree = new Tree(n);
        for (Integer val : nodes) {
            tree.changeNodeWithParent(val);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer val : tree.traversal())
            sb.append(val).append(" ");
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());

        reader.close();
    }
}
