package DP.DPburstBalloons;

/*You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums.
You are asked to burst all the balloons.
If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds
of the array, then treat it as if there is a balloon with a 1 painted on it.
Return the maximum coins you can collect by bursting the balloons wisely.
Example 1:
Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
Example 2:
Input: nums = [1,5]
Output: 10

Intution: Suppose you have been given array [1,2,3,4] which u can see as [1,1,2,3,4,1] (padded by 1 on both side).
So now if u decide to burst baloon with value 3 at last then that means all other baloons will already burst and
so total value to burst the baloons will be = (1 * 3 * 1 + left + right) where left will be max cost of bursting
baloons left to 3 i.e., [1,2] and right will be max cost of bursting baloons t the right of 3 i.e., [4].
So here we can clearly observe that to get the answer for window of size n we need to have the answer for window
of size 2(left) and 1(right) that is smaller subproblems. So here we can definitely think of DP.(Divide and Conquer DP)

Explanation of Approach:
    - Since we need smaller window answer for larger windows, so we have to store the answer for window of each size from 1 to n.
    - Then we will have two pointers left and right to point at the two ends of our current window. For example if we have
        array [1,1,2,3,4,5,1] and we have to get the answer for subarray [2,3,4] then left will point at index 2and right at index 4.
    - Now in the current window we have to burst baloons in such sequence that we get the max value. And for this we have to
        check for each baloon in that window whether it can give the max value if burst at last.
    - So for this we have to traverse from left to right in the window and each time calculate the value assuming ith
        baloon is burst at last.
    - So while filling Dp we will be filling values for left to right window , i.e., dp[left][right]
        = Max(already calculated value, burst this ith baloon last and add left and right subarray points within the window)
    - dp[left][right] = max(dp[left][right], arr[left-1] * arr[i] * arr[right+1] + dp[left][i-1] + dp[i+1][right])

So now we just need to apply 3 loops:

    - 1st for window size from 1 to n
    - then for left pointer from 1 to (n-window+1)
    - Now right is already fixed i.e., (left+window-1), so now third loop from left to right.
    - Each time update the value in dp for left to right.
    - Time Complexity : O(N^3)*/

public class Main {

    // DP
    static int maxCoins(int[] nums) {
        int n = nums.length;
        int arr[] = new int[n+2];
        arr[0] = arr[n+1] = 1;   // Giving padding of 1 to the corner elements
        for(int i=1;i<=n;i++){
            arr[i] = nums[i-1];   //final padded array
        }

        int dp[][] = new int[n+2][n+2];

        for(int window = 1;window<=n;window++){     // window size

            for(int left = 1;left<=n-window+1;left++){    // left pointer

                int right = left+window-1;               // right pointer

                for(int i=left;i<=right;i++){           // iterate from left to right

                    dp[left][right] = Math.max(dp[left][right],
                            (arr[left-1]*arr[i]*arr[right+1]) + dp[left][i-1] + dp[i+1][right]);

                }
            }
        }
        return dp[1][n];
    }

    class SolutionMemorization {
        public int maxCoins(int[] nums) {
            int n = nums.length;
            int arr[] = new int[n+2];
            arr[0] = arr[n+1] = 1;
            for(int i=1;i<=n;i++){
                arr[i] = nums[i-1];
            }

            int memo[][] = new int[n+2][n+2];
            return burst(memo, arr, 0, n + 1);

        }

        public int burst(int[][] memo, int[] nums, int left, int right) {
            if (left + 1 == right) return 0;

            if (memo[left][right] > 0) return memo[left][right];

            int ans = 0;

            for (int i = left + 1; i < right; ++i){
                ans = Math.max(ans, nums[left] * nums[i] * nums[right]
                        + burst(memo, nums, left, i) + burst(memo, nums, i, right));
            }
            memo[left][right] = ans;

            return ans;
        }

    }


    public static void main(String[] args) {
        int[] nums = {3,1,5,8};
        System.out.println(maxCoins(nums));
    }
}
