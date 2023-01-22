package Custom_Impl.TRIE_dfsDictionary;

import java.util.*;

public class WordDictionary {
    private TrieNode root;
    private int R;
    Map<String, Boolean> memo = new HashMap<>();

    public WordDictionary() {
        this.root = new TrieNode();
        this.R = root.getR();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch,new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    public boolean search(String word) {
        return dfs(word,0,root);
    }

    private boolean dfs(String word, int index, TrieNode node) {
        if (index==word.length()) return node.isEnd;

        char ch = word.charAt(index);
        if (ch != '.') {
            TrieNode child = node.get(ch);
            return child==null ? false : dfs(word,index + 1, child);
        }

        // ch == '.' -> look into every possible character in children[]
        for (int i=0; i<R; i++) {
            char temp = (char) ('a'+ i);
            if (node.containsKey(temp) && dfs(word, index + 1, node.get(temp))) {
                return true;
            }
        }

        return false;
    }
}
