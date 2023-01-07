package Math.MathSumSubtractA_B;

/*Given two integers a and b, return the sum of the two integers without using the operators + and -.
Example 1:
Input: a = 1, b = 2
Output: 3

"&" AND operation, for example, 2 (0010) & 7 (0111) => 2 (0010)
"^" XOR operation, for example, 2 (0010) ^ 7 (0111) => 5 (0101)
"~" NOT operation, for example, ~2(0010) => -3 (1101) what??? Don't get frustrated here. It's called two's complement.
1111 is -1, in two's complement
1110 is -2, which is ~2 + 1, ~0010 => 1101, 1101 + 1 = 1110 => 2
1101 is -3, which is ~3 + 1
so if you want to get a negative number, you can simply do ~x + 1

For this, problem, for example, we have a = 1, b = 3,
In bit representation, a = 0001, b = 0011,
First, we can use "and"("&") operation between a and b to find a carry.
carry = a & b, then carry = 0001
Second, we can use "xor" ("^") operation between a and b to find the different bit, and assign it to a,
Then, we shift carry one position left and assign it to b, b = 0010.
Iterate until there is no carry (or b == 0)*/

public class Main {
    static class MathOperations {
        // Iterative
        public int getSum(int a, int b) {
            if (a == 0) return b;
            if (b == 0) return a;

            while (b != 0) {
                int carry = a & b;
                a = a ^ b;
                b = carry << 1;
            }
            return a;
        }

        // Iterative
        public int getSubtract(int a, int b) {
            while (b != 0) {
                int borrow = (~a) & b;
                a = a ^ b;
                b = borrow << 1;
            }

            return a;
        }

        // Get negative number
        public int negate(int x) {
            return ~x + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MathOperations().getSubtract(5,6));
    }
}
