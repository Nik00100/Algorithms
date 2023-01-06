package leetcode3.MathPowerFour;
/*A number n is a power of 4 if the following conditions are met.
 1. There is only one bit set in the binary representation of n (or n is a power of 2)
 2. The bits don’t AND(&) any part of the pattern 0xAAAAAAAA

For example: 16 (10000) is power of 4 because there is only one
bit set and 0x10 & 0xAAAAAAAA is zero.
Note :
Why 0xAAAAAAAA ? This is because the bit representation is
of powers of 2 that are not of 4. Like 2, 8, 32 so on.*/

public class Main {
    public boolean isPowerOfFour(int n)
    {
        if(n<=0)
            return false;
        return n!=0 && ((n&(n-1))==0) && (n&0xAAAAAAAA)==0;
    }
}
