package Math.MathCompareTwoWords;

/*Logic:
The approach itself is quite simple: check if two words share no letters, compute the product of their
lengths and update a global max if it's larger. The tricky part is finding out an efficient way to
compare the two words. Actually, turns out it's quite simple!
All we're concerned about is whether two words have entirely distinct letters. We don't mind how many
letters there are, nor the order of the letters. With this in mind, we can use a 26-bit integer to
represent a bitmask for each word where each bit represents letters 'a' to 'z'. If we find a certain
letter to exist in a word, we simply set that respective bit on the bitmask to 1.

Example:
If we had the string "leetcode", it's bitmask would be: 00000010000000100000011100 where each bit
represents 'a' to 'z' from right to left (keep in mind this bitmask would be stored as the integer
526364 since it's stored as a base-10 integer).'

Now say we had another string "maximum" who's bitmask would be: 00100100000001000100000001.
Notice how the bitmasks share no set-bits in common. This implies that neither word shares a common
letter. So what do you think would happen if we performed a bitwise-AND operation on these two masks?
We get 0.

Therefore, the trick is that if two words share no letters in common, AND-ing them together would result
in zero. Otherwise, it would be non-zero.*/

public class Main {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];

        for (int i = 0; i < n; i++)
            for (char c : words[i].toCharArray())
                masks[i] |= (1 << (c - 'a'));

        int largest = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                if ((masks[i] & masks[j]) == 0)
                    largest = Math.max(largest, words[i].length() * words[j].length());

        return largest;
    }

}
