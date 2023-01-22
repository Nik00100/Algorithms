package Custom_Impl.TRIE_MaxArrayPairXor;

/*Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
Example 1:
Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.
Example 2:
Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
Output: 127

Let's try build Trie and find max XOR using Trie*/

public class Main {
    static class TrieNode {
        // next[0] for storing TrieNode if bit==1, and next[1] for storing TrieNode if bit==0
        TrieNode[] next;
        public TrieNode() {this.next = new TrieNode[2];}
    }

    static void buildTrie(int[] nums, TrieNode root) {
        TrieNode node ;
        for (int num : nums) {
            // starting from root
            node = root;
            // start from 30th bit, because 31th bit always 1 (num>=0)
            for (int i=30; i>=0; i--) {
                int bit = findIthBit(num, i);
                // If bit==0 choose next[1] element, else choose next[0]
                if (node.next[bit^1]==null) {
                    node.next[bit^1]= new TrieNode();
                }
                node = node.next[bit^1];
            }
        }
    }

    static int findIthBit(int num, int i) {
        return (num & (1<<i)) == 0 ? 0 : 1;
    }

    static int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        // build Trie
        buildTrie(nums,root);
        int ans = Integer.MIN_VALUE;
        TrieNode node ;
        for (int num : nums) {
            // starting from root
            node = root;
            // current result
            int cur = 0;
            // also from 30th bit
            for (int i=30; i>=0; i--) {
                int bit = findIthBit(num, i);
                // If find node
                if (node.next[bit]!=null) {
                    node = node.next[bit];
                    cur += 1<<i;
                } else { // If not find node
                    node = node.next[bit^1];
                }
            }
            // update ans
            ans = Math.max(ans, cur);
            // finish if ans == Max value
            if (ans==Integer.MAX_VALUE) return ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {14,70,53,83,49,91,36,80,92,51,66,70};
        System.out.println(findMaximumXOR(nums));
    }
}
