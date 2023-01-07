package CustomImpl.customTRIE_dfsDictionary;

public class TrieNode {
    boolean isEnd;
    TrieNode[] children;
    private final int R = 26;
    public TrieNode() {
        this.isEnd = false;
        this.children = new TrieNode[R];
    }
    public boolean containsKey(char ch) {
        return children[ch -'a'] != null;
    }
    public TrieNode get(char ch) {
        return children[ch -'a'];
    }
    public int getR() {
        return R;
    }
    public void put(char ch, TrieNode node) {
        children[ch -'a'] = node;
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
}
