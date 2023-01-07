package tink.task2Currency;

import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("input2.txt");
        OutputStream outputStream = System.out;
        InputReader inputReader = new InputReader(inputStream);
        OutputWriter outputWriter = new OutputWriter(outputStream);
        Currency currency = new Currency();
        currency.solve(inputReader, outputWriter);

    }

    static class Currency {
        public void solve(InputReader in, OutputWriter out) throws IOException {
            String[] stringWorth = in.readLine().split(" ");
            int[] worth = new int[stringWorth.length];
            worth[0] = Integer.parseInt(stringWorth[0]);
            int lcm = worth[0];
            for (int i = 0; i < stringWorth.length; i++) {
                worth[i] = Integer.parseInt(stringWorth[i]);
                lcm = LCM(lcm, worth[i]);
            }

            String[] stringCurrency = in.readLine().split(" ");

            int[] currency = new int[stringCurrency.length];

            for (int i = 0; i < stringCurrency.length; i++) {
                currency[i] = Integer.parseInt(stringCurrency[i]);

            }

            int balance = getBalance(lcm, currency, worth);

            out.println(threeSumm(balance, worth, lcm).size());

            in.close();
            out.close();
        }

        public List<List<Integer>> threeSumm(int balance, int[] worth, int lcm) {
            int[] nums = new int[balance + 1];
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                nums[i] = i;
            }
            // Result list
            List<List<Integer>> triplets = new ArrayList<>();
            int a = worth[0];
            int b = worth[1];
            int c = worth[2];

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j)
                    for (int k = 0; k < n; ++k) {
                        int x = nums[i];
                        int y = nums[j];
                        int z = nums[k];

                        if (x % (lcm / a) == 0 && y % (lcm / b) == 0 && z % (lcm / c) == 0 && x + y + z == balance)
                            triplets.add(Arrays.asList(x / (lcm / a), y / (lcm / b), z / (lcm / c)));
                    }
            }
            return triplets;
        }

        private int LCM(int x, int y) {
            int a = x;
            int b = y;
            while (y != 0) {
                int tmp = y;
                y = x % y;
                x = tmp;
            }

            return Math.abs(a * b) / x;
        }

        private int getBalance(int lcm, int[] currency, int[] worth) {
            int res = 0;
            for (int i = 0; i < currency.length; i++) {
                res += currency[i] * (lcm / worth[i]);
            }
            return res;
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
            this.outputStream = outputStream;
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
