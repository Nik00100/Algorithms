package Custom_Impl.TRIE_replaceWords;

/*648. Replace Words

In English, we have a concept called root, which can be followed by some other word to form another longer word -
let's call this word successor. For example, when the root "an" is followed by the successor word "other",
we can form a new word "another". Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root,
replace it with the root that has the shortest length. Return the sentence after the replacement.
Example 1:
Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Example 2:
Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
Output: "a a b c"*/

import java.util.*;


public class Main {
    public static String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();

        // build Trie from dictictionary
        for (String word : dictionary) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
            }
            node.word = word;
        }


        // search and then replace if possible words from sentence to dictionary
        StringBuilder sb = new StringBuilder();
        for (String str : sentence.split(" ")) {
            if (sb.length() > 0) sb.append(" ");
            TrieNode node = root;
            for (char ch : str.toCharArray()) {
                if (node.children[ch - 'a'] == null || node.word != null) {
                    break;
                }
                node = node.children[ch - 'a'];
            }
            String word = node.word != null ? node.word : str;
            sb.append(word);
        }
        return sb.toString();
    }

    public static class TrieNode {
        TrieNode[] children;
        String word;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        System.out.println(replaceWords(Arrays.stream(new String[] {"cat","bat","rat"}).toList(), "the cattle was rattled by the battery"));

    }
}
