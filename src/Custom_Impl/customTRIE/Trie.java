package Custom_Impl.customTRIE;

public class Trie {
    private TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i=0; i<word.length(); i++) {
            char ch =word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch,new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    // search a prefix or whole key in trie and returns the node where search ends
    public TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (int i=0; i<prefix.length(); i++) {
            char ch =prefix.charAt(i);
            if (node.containsKey(ch)) {
                node=node.get(ch);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node!=null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node!=null;
    }
}
