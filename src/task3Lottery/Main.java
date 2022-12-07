package task3Lottery;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader inputReader = new InputReader(inputStream);
        OutputWriter outputWriter = new OutputWriter(outputStream);
        Lottery lottery = new Lottery();
        lottery.solve(inputReader,outputWriter);

    }

    static class Lottery {
        public void solve(InputReader in, OutputWriter out) throws IOException {
            int n = in.readIntFromLine();
            long minLCM = Long.MAX_VALUE;
            int index = -1;
            for (int i=1; i<=n/2; i++) {
                if (minLCM > lcm(i,n-i)) {
                    minLCM = lcm(i,n-i);
                    index=i;
                }
            }

            out.print(index);
            out.print(" ");
            out.print(n-index);
            in.close();
            out.close();
        }

        private long gcd(long a, long b)
        {
            while (b > 0)
            {
                long temp = b;
                b = a % b; // остаток
                a = temp;
            }
            return a;
        }

        private long lcm(long a, long b)
        {
            return a * (b / gcd(a, b));
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

