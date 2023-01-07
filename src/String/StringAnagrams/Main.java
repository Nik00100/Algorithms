package String.StringAnagrams;

import java.io.*;

class Main {
    static boolean isAnagram(String s, String t) {
        int[] chars = new int['z'-'a'+1];
        for(char c: s.toCharArray()) {chars[c - 'a']++;}
        for(char c: t.toCharArray()) {chars[c - 'a']--;}
        for(int count: chars) { if(count != 0) {return false;}}
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String t = reader.readLine();
        if (isAnagram(s,t)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}