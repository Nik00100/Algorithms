package ya_int.cypher;

import java.io.*;
import java.util.*;

public class Main {
    public static int getCharCountInFIO (String[] fio) {
        Set<Character> set = new HashSet<>();
        for (String word : fio) {
            for (char ch : word.toCharArray())
                set.add(ch);
        }
        return set.size();
    }

    public static int getFirstCharValue (String surname) {
        return (1 + surname.charAt(0) - 'A')*256;
    }

    public static int getSumOfNumsInStrValue (String str) {
        int res = 0;
        for (String n : str.split("")) {
            res += Integer.parseInt(n);
        }
        return res*64;
    }

    public static String getLastThreeFromHex (int value) {
        StringBuilder sb = new StringBuilder(String.format("%X", value));
        if (sb.length() < 3) {
            int n = 3 - sb.length();
            sb.reverse();
            while (n>0) {
                sb.append("0");
                n--;
            }
            sb.reverse();
        }
        if (sb.length() > 3) {
            return sb.substring(sb.length()-3,sb.length());
        }
        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            String[] s = reader.readLine().split(",");
            String[] fio = Arrays.copyOfRange(s,0,3);
            String surname = s[0];
            String day = s[3];
            String month = s[4];

            int value = getCharCountInFIO(fio) + getFirstCharValue(surname) + getSumOfNumsInStrValue(day) + getSumOfNumsInStrValue(month);
            sb.append(getLastThreeFromHex(value)).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
        reader.close();
    }
}
