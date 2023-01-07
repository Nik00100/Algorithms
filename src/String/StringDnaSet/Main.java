package String.StringDnaSet;

/*The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.
Given a string s that represents a DNA sequence, return all the 10-letter-long sequences
(substrings) that occur more than once in a DNA molecule. You may return the answer in any order.

Example 1:
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
Example 2:
Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]*/

import java.util.*;

public class Main {
    static List<String> findRepeatedDnaSequences(String s) {
        Set<String> check = new HashSet<>();
        Set<String> result = new HashSet<>();
        for (int i=0; i+9<s.length(); i++) {
            String temp = s.substring(i,i+10);
            if (!check.add(temp)) result.add(temp);
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
