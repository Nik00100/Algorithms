package ya_training.algo3_0.task1_15_02.divA.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        String temp = string;
        String[] tags = temp.replaceAll("<|>", " ").split(" ");

        if (!checkTags(temp).equals("closed")) {
            List<Character> exchange = new ArrayList<Character>();
            String s = checkTags(temp) ;
                for (int i = 0; i < s.length(); i++) {
                    exchange.add(s.charAt(i));
                }

            exchange.add('<');
            exchange.add('>');
            exchange.add('/');

            char[] tempArr = string.toCharArray();
            List<String> answers = new ArrayList<String>();
            for (int j = 0; j < tempArr.length; j++) {
                for (char c : "<>/qwertyuiopasdfghjklzxcvbnm".toCharArray()) {
                    if (exchange.contains(tempArr[j])) {
                        tempArr[j] = c;
                        String xml = new String(tempArr);
                        if (checkTags(xml).equals("closed")) {
                            String[] newTags = xml.replaceAll("<|>", " ").split(" ");
                            StringBuilder result = new StringBuilder();
                            for (String t : newTags) {
                                result.append("<" + t + ">");
                            }
                            if (result.length() == string.length()) {
                                answers.add(result.toString());
                            }
                        } else {
                            tempArr = string.toCharArray();
                        }
                    }
                }
            }
            System.out.println(answers.stream().max(String::compareTo).get());
        } else {
            StringBuilder result = new StringBuilder();
            for (String t : tags) {
                result.append("<" + t + ">");
            }
            System.out.println(result.toString());
        }
    }

    public static String checkTags(String xml) {
        List<String> stack = new ArrayList<String>();
        String openRight = null;
        String[] tags = xml.replaceAll("<|>", " ").split(" ");
        for (String tag : tags) {
            if (tag.matches("[a-zA-Z]+")) {
                stack.add(tag);
            } else if (tag.startsWith("/") && stack.contains(tag.substring(1))) {
                stack.remove(tag.substring(1));
            } else {
                openRight = tag;
                return openRight;
            }
        }
        if (stack.isEmpty() && openRight == null) {
            return "closed";
        } else {
            return stack.toString();
        }
    }
}
