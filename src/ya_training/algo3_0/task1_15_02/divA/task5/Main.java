package ya_training.algo3_0.task1_15_02.divA.task5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static boolean checkTags(String xml) {
        if (xml.charAt(0) != '<' || xml.charAt(xml.length()-1) != '>') {
            return false;
        }
        ArrayList<String> opening_tag = new ArrayList<>();
        int i = 0;
        while (true) {
            if (i == xml.length()) {
                break;
            }
            if (xml.charAt(i) != '<') {
                return false;
            }
            i++;
            int closing_tag = 0;
            if (xml.charAt(i) == '/') {
                closing_tag  = 1;
                i++;
            }
            if (!(xml.charAt(i) >= 'a' && xml.charAt(i) <= 'z')) {
                return false;
            }
            String tag = String.valueOf(xml.charAt(i));
            i++;
            while (xml.charAt(i) >= 'a' && xml.charAt(i) <= 'z') {
                tag += xml.charAt(i);
                i++;
            }
            if (xml.charAt(i) != '>') {
                return false;
            }
            i++;
            if (closing_tag  == 0) {
                opening_tag.add(tag);
            } else {
                if (opening_tag.size() == 0) {
                    return false;
                }
                if (!opening_tag.get(opening_tag.size()-1).equals(tag)) {
                    return false;
                }
                opening_tag.remove(opening_tag.size()-1);
            }
        }
        return opening_tag.size() == 0;
    }

    public static void changeSymbol(char j, char[] string, ArrayList<String> result) {
        for (int i = 0; i < string.length; i++) {
            char[] temp = string.clone();
            temp[i] = j;
            if (checkTags(String.valueOf(temp))) {
                result.add(String.valueOf(temp));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] string = scanner.nextLine().toCharArray();
        int opened_chevron = 0;
        int closed_chevron = 0;
        int slashes = 0;
        HashMap<Character, Integer> letters = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();

        for (char i : string) {
            letters.put(i, letters.getOrDefault(i, 0) + 1);
            if (i == '<') {
                opened_chevron++;
            } else if (i == '>') {
                closed_chevron++;
            } else if (i == '/') {
                slashes++;
            }
        }

        for (char j : "<>/qwertyuiopasdfghjklzxcvbnm".toCharArray()) {
            if (j == '<' && opened_chevron % 2 != 0) {
                changeSymbol(j, string, result);
            } else if (j == '>' && closed_chevron % 2 != 0) {
                changeSymbol(j, string, result);
            } else if (j == '/' && closed_chevron / 2 != slashes) {
                changeSymbol(j, string, result);
            } else if (letters.containsKey(j) && letters.get(j) % 2 != 0) {
                changeSymbol(j, string, result);
            }
        }

        System.out.println(result.get(0));

    }
}
