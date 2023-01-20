package Custom_Impl.customTree_Traversal_Search;

/*508. Most Frequent Subtree Sum https://leetcode.com/problems/most-frequent-subtree-sum/

Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the
highest frequency in any order. The subtree sum of a node is defined as the sum of all the node values formed by the
subtree rooted at that node (including the node itself).

Example 1:
Input: root = [5,2,-3]
Output: [2,-3,4]
Example 2:
Input: root = [5,2,-5]
Output: [2]*/

import java.util.*;

public class BinaryTreeMaxFreqSubtreeSum {
    static class TreeNode {
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

    static class Solution {
        Map<Integer,Integer> freqMap = new HashMap<>();
        int maxFreq = 0;

        public int findSubtreeSums(TreeNode root) {
            if (root == null)
                return 0;
            // post order traverse and find sum
            int left = findSubtreeSums(root.left);
            int right = findSubtreeSums(root.right);

            int current = root.val + left + right;
            freqMap.put(current,freqMap.getOrDefault(current,0) + 1);
            maxFreq = Math.max(maxFreq, freqMap.get(current));
            return current;
        }
        public int[] findFrequentTreeSum(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            // traverse all nodes and find sums
            findSubtreeSums(root);

            for (Map.Entry<Integer,Integer> entry : freqMap.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();
                if (val == maxFreq) ans.add(key);
            }
            int[] maxFreqSums = ans.stream().mapToInt(a->a).toArray();
            /*int[] maxFreqSums = new int[ans.size()];
            for (int i=0; i<ans.size(); i++) {
                maxFreqSums[i] = ans.get(i).intValue();
            }*/
            return maxFreqSums;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(2), new TreeNode(-3));
        System.out.println(Arrays.stream(new Solution().findFrequentTreeSum(root)).boxed().toList());
    }

}
