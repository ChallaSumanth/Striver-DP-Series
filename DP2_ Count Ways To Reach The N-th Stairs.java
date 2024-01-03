//https://www.codingninjas.com/studio/problems/count-ways-to-reach-the-n-th-stairs_798650?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//https://leetcode.com/problems/climbing-stairs/description/
//1.recursion
class Solution {
    public int climbStairs(int n) {
        if(n < 0)
            return 0;
        if(n == 1 || n == 0)
            return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
//2.memorization
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return countWays(dp, n);
    }
    private int countWays(int[] dp, int n)
    {
        if(n < 0)
            return 0;
        if(n == 0 || n == 1)
            return 1;
        if(dp[n] != -1)
            return dp[n];
        return dp[n] = countWays(dp, n - 1) + countWays(dp, n - 2);
    }
}
//3.Tabulation
class Solution {
    public int climbStairs(int n) {
        if(n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++)
        {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
   
}
//4.Space optimization
class Solution {
    public int climbStairs(int n) {
        if(n == 1)
            return 1;
        int lastStep = 1;
        int secondLastStep = 1;
        for(int i = 2; i <= n; i++)
        {
            int thirdStep = lastStep + secondLastStep;
            lastStep = secondLastStep;
            secondLastStep = thirdStep;
        }
        return secondLastStep;
    }
}