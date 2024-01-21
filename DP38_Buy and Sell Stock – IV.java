//https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock-iv_1080698?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution
{
public static int maximumProfit(int[] prices, int n, int k)
    {
        // Write your code here.
        return maxPrft(0, 1, n, k, prices);
    }

    private static int maxPrft(int idx, int buy, int n, int k, int[] prices)
    {
        if(idx == n || k == 0)
            return 0;
        int profit = 0;
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] + maxPrft(idx + 1, 0, n, k, prices),
                        maxPrft(idx + 1, 1, n, k, prices));
        }
        else
        {
            profit = Math.max(prices[idx] + maxPrft(idx + 1, 1, n, k - 1, prices),
                            maxPrft(idx + 1, 0, n, k, prices));
        }
        return profit;
    }
}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution
{
public static int maximumProfit(int[] prices, int n, int k)
    {
        // Write your code here.
        int[][][] dp = new int[n][2][k + 1];
        for(int[][] rc : dp)
            for(int[] r : rc)
                Arrays.fill(r, -1);
        return maxPrft(0, 1, n, k, prices, dp);
    }

    private static int maxPrft(int idx, int buy, int n, int k, int[] prices, int[][][] dp)
    {
        if(idx == n || k == 0)
            return 0;
        int profit = 0;
        if(dp[idx][buy][k] != -1)
            return dp[idx][buy][k];
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] + maxPrft(idx + 1, 0, n, k, prices, dp),
                        maxPrft(idx + 1, 1, n, k, prices, dp));
        }
        else
        {
            profit = Math.max(prices[idx] + maxPrft(idx + 1, 1, n, k - 1, prices, dp),
                            maxPrft(idx + 1, 0, n, k, prices, dp));
        }
        return dp[idx][buy][k] = profit;
    }
}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution
{
public static int maximumProfit(int[] prices, int n, int k)
    {
        // Write your code here.
        int[][][] dp = new int[n + 1][2][k + 1];
        for(int i = n - 1; i >= 0; i--)
        {
            for(int buy = 0; buy <= 1; buy++)
            {
                for(int t = 1; t <= k; t++)
                {
                    int profit = 0;
                    if(buy == 1)
                    {
                        profit = Math.max(-prices[i] + dp[i + 1][0][t],
                                dp[i + 1][1][t]);
                    }
                    else
                    {
                        profit = Math.max(prices[i] + dp[i + 1][1][t - 1],
                                dp[i + 1][0][t]);
                    }
                    dp[i][buy][t] = profit;
                }
            }
        }
        return dp[0][1][k];
    }
}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution
{
public static int maximumProfit(int[] prices, int n, int k)
    {
        // Write your code here.
        int[][] ahead = new int[2][k + 1];
        int[][] cur = new int[2][k + 1];
        for(int i = n - 1; i >= 0; i--)
        {
            for(int buy = 0; buy <= 1; buy++)
            {
                for(int t = 1; t <= k; t++)
                {
                    int profit = 0;
                    if(buy == 1)
                    {
                        profit = Math.max(-prices[i] + ahead[0][t],
                                ahead[1][t]);
                    }
                    else
                    {
                        profit = Math.max(prices[i] + ahead[1][t - 1],
                                ahead[0][t]);
                    }
                    cur[buy][t] = profit;
                }
               
            }
             ahead = cur;
        }
        return ahead[1][k];
    }
}

