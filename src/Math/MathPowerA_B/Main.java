package Math.MathPowerA_B;

/*Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large
positive integer given in the form of an array.
Example 1:
Input: a = 2, b = [3]
Output: 8
Example 2:
Input: a = 2, b = [1,0]
Output: 1024

We have to find (a^b) mod 1337

By Eulers Theorem : 𝑎^𝜙(1337) ≡ 1 mod 1337 where 𝜙() is Euler's Toitent Function
Now, 𝜙(1337) = 1140 (See References)

𝑎^𝜙(1337) ≡ 1 mod 1337 ⇒ 𝑎^1140 ≡ 1 mod 1337 ⇒ Multiply with a^(b/1140) on both sides
⇒ a^b ≡ (a^(b/1140) mod m * a ^ (b % 1140) mod m

Now, Since 𝑎^1140 ≡ 1 mod 1337, it can proven that a^(1140n) ≡ 1 mod 1337
⇒ a^b ≡ 1 * a ^ (b % 1140) mod m

Since, b[] is an array and we need to find the mod of actual power, for every digit we need to find
(digit * 10^place ) % 1140 and add this to result of modded power

We can use binary exponentiation with modular operations to find this.*/

public class Main {
    static int superPow(int a, int[] b) {
        int num=0;
        for(int i:b){
            num=(num*10+i)%1140;
        }
        return binexpo(a,num,1337);
    }
    static int binexpo(int a, int b, int m){
        a%=m;
        int res=1;
        while(b>0){
            if((b&1)==1)
                res=(res*a)%m;
            a=(a*a)%m;
            b>>=1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(superPow(3,new int[]{2,5}));
    }
}
