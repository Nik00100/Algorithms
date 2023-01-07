package String.StringMathPeriodicFraction;

/*Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
If the fractional part is repeating, enclose the repeating part in parentheses.
If multiple answers are possible, return any of them.
It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:
Input: numerator = 2, denominator = 1
Output: "2"
Example 3:
Input: numerator = 4, denominator = 333
Output: "0.(012)"*/

import java.util.HashMap;
import java.util.Map;

public class Main {
    static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        long num = (long) Math.abs(numerator);
        long den = denominator != Integer.MIN_VALUE ? (long) Math.abs(denominator) : Integer.MAX_VALUE;
        String sign = (numerator > 0)^(denominator > 0) ? "-" : "";
        sb.append(sign).append(num / den);
        num %= den;
        if (num == 0) return sb.toString(); // если нет дробной части, возвращаем строковое представление

        // если есть дробная часть, используем HashMap
        sb.append(".");
        Map<Long,Integer> map = new HashMap<>();
        map.put(num,sb.length());
        while(num != 0) {
            num *=10;
            sb.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                sb.insert(index,"(");
                sb.append(")");
                break;
            } else {
                map.put(num,sb.length());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(-4,333));
    }
}

