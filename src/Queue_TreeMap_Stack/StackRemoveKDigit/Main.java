package Queue_TreeMap_Stack.StackRemoveKDigit;

/*Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer
after removing k digits from num.

Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.*/

import java.util.Stack;

public class Main {

    public String removeKdigits(String num, int k) {
        if (k == num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        // if the previous character in stk is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        while (i < num.length()) {
            //check whether the current num is smaller than the top of the stack (means we have the peak)
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                //eliminate the peak
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }
        // handles the scenario where digits are equal, (1111, k=3)
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        //as stack follows LIFO
        sb.reverse();

        //remove leading zeros
        while (sb.toString().startsWith("0")) {
            sb.deleteCharAt(0);

        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    //Time Complexity: O(N)
}
