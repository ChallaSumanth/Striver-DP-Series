//https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock-iii_1071012?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    public static int maxProfit(int[] prices) {
        // Write your code here.
        int len = prices.length;

        return maxprft(0, len, prices, 1, 2);
    }

    private static int maxprft(int idx, int len, int[] prices, int buy, int cap)
    {
        if(idx == len || cap == 0)
            return 0;
        int profit = 0;
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] + maxprft(idx + 1, len, prices, 0, cap),
                        maxprft(idx + 1, len, prices, 1, cap));
        }
        else
        {
            profit = Math.max(prices[idx] + maxprft(idx + 1, len, prices, 1, cap - 1),
                        maxprft(idx + 1, len, prices, 0, cap));
        }
        return profit;
    }
}
//2.memorization
import java.util.Arrays;

public class Solution {
    public static int maxProfit(int[] prices) {
        // Write your code here.
        int len = prices.length;
        int[][][] dp = new int[len][2][3];
        for(int[][] rc : dp)
            for(int[] r : rc)
                Arrays.fill(r, -1);
        return maxprft(0, len, prices, 1, 2, dp);
    }

    private static int maxprft(int idx, int len, int[] prices, int buy, int cap, int[][][] dp)
    {
        if(idx == len || cap == 0)
            return 0;
        int profit = 0;
        if(dp[idx][buy][cap] != -1)
            return dp[idx][buy][cap];
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] + maxprft(idx + 1, len, prices, 0, cap, dp),
                        maxprft(idx + 1, len, prices, 1, cap, dp));
        }
        else
        {
            profit = Math.max(prices[idx] + maxprft(idx + 1, len, prices, 1, cap - 1, dp),
                        maxprft(idx + 1, len, prices, 0, cap, dp));
        }
        return dp[idx][buy][cap] = profit;
    }
}
//3.tabulation
import java.util.Arrays;

public class Solution {
    public static int maxProfit(int[] prices) {
        // Write your code here.
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];
        for(int idx = n - 1; idx >= 0; idx--)
        {
            for(int buy = 0; buy <= 1; buy++)
            {
                for(int cap = 1; cap <= 2; cap++)
                {
                    int profit = 0;
                    if(buy == 1)
                    {
                        profit = Math.max(-prices[idx] + dp[idx + 1][0][cap],
                                            dp[idx + 1][1][cap]);
                    }
                    else
                    {
                        profit = Math.max(prices[idx] + dp[idx + 1][1][cap - 1],
                                            dp[idx + 1][0][cap]);
                    }
                    dp[idx][buy][cap] = profit;
                }
            }
        }
        return dp[0][1][2];
    }
}
//4.space optimization
import java.util.Arrays;

public class Solution {
    public static int maxProfit(int[] prices) {
        // Write your code here.
        int n = prices.length;
        int[][] ahead = new int[2][3];
        for(int idx = n - 1; idx >= 0; idx--)
        {
            int[][] cur = new int[2][3];
            for(int buy = 0; buy <= 1; buy++)
            {
                for(int cap = 1; cap <= 2; cap++)
                {
                    int profit = 0;
                    if(buy == 1)
                    {
                        profit = Math.max(-prices[idx] + ahead[0][cap],
                                            ahead[1][cap]);
                    }
                    else
                    {
                        profit = Math.max(prices[idx] + ahead[1][cap - 1],
                                            ahead[0][cap]);
                    }
                    cur[buy][cap] = profit;
                }
            }
            ahead = cur;
        }
        return ahead[1][2];
    }
}