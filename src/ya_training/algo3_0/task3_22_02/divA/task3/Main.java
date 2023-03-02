package ya_training.algo3_0.task3_22_02.divA.task3;

import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = n / 2;

        BigInteger s = BigInteger.ZERO;
        BigInteger kBigInt = BigInteger.valueOf(k);
        BigInteger four = BigInteger.valueOf(4);
        BigInteger seven = BigInteger.valueOf(7);

        if (n % 2 == 0) {
            BigInteger numerator = kBigInt.multiply(kBigInt.add(BigInteger.ONE))
                    .multiply(four.multiply(kBigInt).add(BigInteger.ONE));
            s = numerator.divide(BigInteger.TWO);
        } else {
            BigInteger numerator = kBigInt.add(BigInteger.ONE)
                    .multiply(four.multiply(kBigInt).multiply(kBigInt)
                            .add(seven.multiply(kBigInt)).add(BigInteger.TWO));
            s = numerator.divide(BigInteger.TWO);
        }

        System.out.println(s);
    }

}
