package BFS_DFS_UnionFind.DfsConcatenatedWords;

/*472. Concatenated Words https://leetcode.com/problems/concatenated-words/

Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
Example 1:
Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
"dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

Intuition
As mentioned before, this problem can be transformed into a reachability problem and thus can be solved by a DFS (or BFS) algorithm.
For each word, we construct a directed graph with all prefixes as nodes. For simplicity, we can represent each prefix by its length.

So the graph contains (word.length + 1) nodes. For edges, consider 2 prefixes i and j with 0 <= i < j <= word.length,
if prefix j can be created by concatenating prefix i and a word in the dictionary, we add a directed edge from node i to node j.

When i = 0, we require j < word.length as there should be an edge from node 0 to node word.length. Determining whether
a word can be created by concatenating 2 or more words in the dictionary is the same as determining whether there is
a path from node 0 to node word.length in the graph.

Algorithm
For each word, construct the implicit graph mentioned above, then add it to the answer if the node word.length can be
reached from node 0 in the graph which can be checked using DFS.*/

import java.util.*;

public class Main {
    static class Solution {
        private boolean dfs(String word, int length, boolean[] visited, Set<String> dictionary) {
            if (length == word.length()) {
                return true;
            }
            if (visited[length]) {
                return false;
            }
            visited[length] = true;
            for (int i = word.length() - (length == 0 ? 1 : 0); i > length; --i) {
                if (dictionary.contains(word.substring(length, i))
                        && dfs(word, i, visited, dictionary)) {
                    return true;
                }

            }
            return false;
        }

        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            Set<String> dictionary = new HashSet<>(Arrays.asList(words));
            List<String> answer = new ArrayList<>();
            for (String word : words) {
                int length = word.length();
                boolean[] visited = new boolean[length];
                if (dfs(word, 0, visited, dictionary)) {
                    answer.add(word);
                }
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        System.out.println(new Solution().findAllConcatenatedWordsInADict(words));
    }
}
