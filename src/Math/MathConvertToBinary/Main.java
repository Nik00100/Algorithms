package Math.MathConvertToBinary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i, j, max, num, numDigits, digit;
        int[] digits = new int[20];

        System.out.print("Enter a number: ");
        num = scanner.nextInt();
        max = num;
        numDigits = 0;

        // convert decimal to binary and store each digit in an array
        while (num > 0) {
            numDigits++;
            digits[numDigits] = num % 2;
            num /= 2;
        }

        for (i = 1; i <= numDigits - 1; i++) {
            // rotate digits array by one position
            digit = digits[1];
            for (j = 2; j <= numDigits; j++) {
                digits[j-1] = digits[j];
            }
            digits[numDigits] = digit;

            // convert binary back to decimal
            num = 0;
            for (j = numDigits; j >= 1; j--) {
                num = num * 2 + digits[j];
            }
            if (num > max) {
                max = num;
            }
        }
        System.out.println("The maximum number obtained after rotating the binary representation: " + max);
    }
}
