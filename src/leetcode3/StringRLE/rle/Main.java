package leetcode3.StringRLE.rle;

public class Main {
    public static String getRLE(String input) {
        StringBuilder result = new StringBuilder();
        int lengthOfInput = input.length();
        char lastCharacter = input.charAt(0);
        int lastCharacterCount = 1;

        // we will go until equal to the length of input and we will do the final flush inside the loop
        // in this way we will have roughly 90% - 95% performance improvent over nested iteration
        // but this might take a little more memory as compared to the nested loop iteration
        for (int i = 1; i <= lengthOfInput; i++) {

            if (i == lengthOfInput) {
                // we have already completed everything let us append the final value of i - 1 iteration
                result.append(lastCharacter).append(lastCharacterCount);
                break;
            }

            char currentCharacter = input.charAt(i);

            if (lastCharacter == currentCharacter) {
                lastCharacterCount++;
            } else {
                result.append(lastCharacter).append(lastCharacterCount);
                lastCharacter = currentCharacter;
                lastCharacterCount = 1;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRLE("aaaaaaaaaaaabbbccca"));
    }
}
