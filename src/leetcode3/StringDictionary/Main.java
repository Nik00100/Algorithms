package leetcode3.StringDictionary;

/*Sample Input ["eat", "tea", "tan", "ate", "nat", "bat"]
Sample Output [ ["ate", "eat", "tea"], ["nat", "tan"], ["bat"] ]

Т.е. сгруппировать слова по "общим буквам".*/

import java.util.*;

public class Main {
    static List<List<String>> getDict(String[] words) {
        Map<String,List<String>> result = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<String> list = new ArrayList<>();
            if (result.containsKey(key)) {
                list = result.get(key);
                list.add(word);
                result.put(key,list);
            } else {
                list.add(word);
                result.put(key,list);
            }
        }
        return result.values().stream().toList();
    }

    public static void main(String[] args) {
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(getDict(words));
    }
}
