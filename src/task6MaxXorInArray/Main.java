package task6MaxXorInArray;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("input6.txt");
        OutputStream outputStream = System.out;
        InputReader inputReader = new InputReader(inputStream);
        OutputWriter outputWriter = new OutputWriter(outputStream);
        Xor xor = new Xor();
        xor.solve(inputReader,outputWriter);

    }

    static class Xor {
        public void solve(InputReader in, OutputWriter out) throws IOException {
            int n = in.readIntFromLine();
            List<Integer> nums = new ArrayList<>();

            for (int i=0; i<n; i++) {
                nums.add(in.readIntFromLine());
                out.println(findMaximumXOR(nums));
            }
            in.close();
            out.close();
        }

        int findMaximumXOR(List<Integer> nums) {
            final int maxNum = nums.stream().mapToInt(Integer::intValue).max().getAsInt();
            if (maxNum == 0)
                return 0;
            final int maxBit = (int) (Math.log(maxNum) / Math.log(2));
            int ans = 0;
            int mask = 0;

            // If ans is 11100 when i = 2, it means that before we reach the last two
            // bits, 11100 is the maximum XOR we have, and we're going to explore if we
            // can get another two '1's and put them into ans.
            for (int i = maxBit; i >= 0; --i) {
                // Mask grows like: 100...000, 110...000, 111...000, ..., 111...111.
                mask |= 1 << i;
                Set<Integer> prefixes = new HashSet<>();
                // We only care about the left parts,
                // If i = 2, nums = {1110, 1011, 0111}
                //    . prefixes = {1100, 1000, 0100}
                for (final int num : nums)
                    prefixes.add(num & mask);
                // If i = 1 and before this iteration, the ans is 10100, it means that we
                // want to grow the ans to 10100 | 1 << 1 = 10110 and we're looking for
                // XOR of two prefixes = candidate.
                final int candidate = ans | 1 << i;
                for (final int prefix : prefixes)
                    if (prefixes.contains(prefix ^ candidate)) {
                        ans = candidate;
                        break;
                    }
            }
            return ans;
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

