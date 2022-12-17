package yandex.gems;

/*Даны две строки строчных латинских символов: строка J и строка S. Символы,
входящие в строку J, — «драгоценности», входящие в строку S — «камни». Нужно определить,
какое количество символов из S одновременно являются «драгоценностями».
Проще говоря, нужно проверить, какое количество символов из S входит в J.*/

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String j = reader.readLine();
        String s = reader.readLine();


        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (j.indexOf(ch) >= 0) {
                ++res;
            }
        }

        writer.write(String.valueOf(res));

        reader.close();
        writer.close();
    }

}
