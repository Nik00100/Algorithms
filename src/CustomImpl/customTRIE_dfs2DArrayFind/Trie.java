package CustomImpl.customTRIE_dfs2DArrayFind;

import java.util.*;

public class Trie {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    int[] dirs = {1, 0, -1, 0, 1};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for(int i=0; i< board.length; i++)
            for (int j=0; j< board[0].length; j++)
                dfs(board,i,j,root,ans);
        return ans;
    }

    public void dfs(char[][] board, int i, int j, TrieNode node, List<String> ans) {
        if (i<0 || j<0 || i> board.length-1 || j > board[0].length-1) return;

        char ch = board[i][j];
        if (ch=='$' || node.children[ch-'a']==null) return;

        node = node.children[ch-'a'];
        if (node.word!=null) {
           ans.add(node.word);
           node.word = null;
        }

        // set character as visited
        board[i][j] = '$';

        // traverse nearest nodes
        for (int k=0; k<4; k++) {
            dfs(board,i+dirs[k],j+dirs[k+1],node,ans);
        }

        // return back character
        board[i][j] = ch;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int i = ch - 'a';
                if (node.children[i] == null) {
                    node.children[i] = new TrieNode();
                }
                node = node.children[i];
            }
            node.word = word;
        }
        return root;
    }

}
