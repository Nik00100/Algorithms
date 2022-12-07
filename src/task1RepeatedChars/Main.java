package task1RepeatedChars;

import java.io.*;
import java.util.regex.*;


public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("input1.txt");
        OutputStream outputStream = System.out;
        InputReader inputReader = new InputReader(inputStream);
        OutputWriter outputWriter = new OutputWriter(outputStream);
        WordCheck wordCheck = new WordCheck();
        wordCheck.solve(inputReader,outputWriter);

    }

    static class WordCheck {


        public void solve(InputReader in, OutputWriter out) throws IOException {
            int n = in.readIntFromLine();
            String departmentName = in.readLine();
            String painting = in.readLine();
            if (departmentName.replaceAll(" ","").length() != n || painting.length() != n) throw new IOException("Неверная длина строки");
            out.println(calculate1(painting));
            in.close();
            out.close();
        }


        private int calculate(String str) {
            int n = str.length();
            int result = 0;
            int i=0;
            while (i+1<n) {
                char temp = str.charAt(i);
                if (str.charAt(i+1) != temp) {
                    i++;
                    continue;
                }
                while (i+1<n && str.charAt(i+1) == temp) {
                    i++;
                }
                result++;
            }
            return result;
        }

        private int calculate1 (String str) {
            int result = 0;
            Matcher m = Pattern.compile("(.)\\1+").matcher(str);
            while (m.find()) {
                result++;
            }
            return result;
        }

    }

    static class InputReader {
        private InputStream stream;

        private BufferedReader bufferedReader;

        public InputReader(InputStream stream) {
            this.bufferedReader = new BufferedReader(new InputStreamReader(stream));
            this.stream = stream;
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public int readIntFromLine() throws IOException {
            return Integer.parseInt(bufferedReader.readLine());
        }

        public void close() throws IOException {
            bufferedReader.close();
            stream.close();
        }
    }

    static class OutputWriter {
        private OutputStream outputStream;
        private PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            this.outputStream=outputStream;
            this.writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public void print(int n) {
            writer.write(String.valueOf(n));
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print("");
                }
                writer.print(objects[i].toString());
            }
        }

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() throws IOException {
            writer.close();
            outputStream.close();
        }
    }
}
