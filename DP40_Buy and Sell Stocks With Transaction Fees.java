//https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock-with-transaction-fee_3118974?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    public static int maximumProfit(int[] prices, int n, int fee) {
        // Write your code here.
        return maxPrft(0, n, prices, 1, fee);
    }

     private static int maxPrft(int idx, int n, int[] prices, int buy, int fee)
    {
        if(idx == n)
            return 0;
        int profit = 0;
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] + maxPrft(idx + 1, n, prices, 0, fee),
                            maxPrft(idx + 1, n, prices, 1, fee));
        }
        else
        {
            profit = Math.max((prices[idx] - fee) + maxPrft(idx + 1, n, prices, 1, fee),
                                maxPrft(idx + 1, n, prices, 0, fee));
        }
        return profit;
    }
}
//2.memorization
import java.util.Arrays;

public class Solution {
    public static int maximumProfit(int[] prices, int n, int fee) {
        // Write your code here.
        int[][] dp = new int[n + 1][2];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return maxPrft(0, n, prices, 1, fee, dp);
    }

     private static int maxPrft(int idx, int n, int[] prices, int buy, int fee, int[][] dp)
    {
        if(idx >= n)
            return 0;
        int profit = 0;
        if(dp[idx][buy] != -1)
            return dp[idx][buy];
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] + maxPrft(idx + 1, n, prices, 0, fee, dp),
                            maxPrft(idx + 1, n, prices, 1, fee, dp));
        }
        else
        {
            profit = Math.max((prices[idx] - fee) + maxPrft(idx + 1, n, prices, 1, fee, dp),
                                maxPrft(idx + 1, n, prices, 0, fee, dp));
        }
        return dp[idx][buy] = profit;
    }
}
//3.tabulation
import java.util.Arrays;

public class Solution {
    public static int maximumProfit(int[] prices, int n, int fee) {
        // Write your code here.
        int[][] dp = new int[n + 1][2];
        for(int i = n - 1; i >= 0; i--)
        {
            for(int buy = 0; buy <= 1; buy++)
            {
                int profit = 0;
                if(buy == 1)
                {
                    profit = Math.max(-prices[i] + dp[i + 1][0],
                            dp[i + 1][1]);
                }
                else
                {
                    profit = Math.max((prices[i] - fee) + dp[i + 1][1],
                                dp[i + 1][0]);
                }
                dp[i][buy] = profit;
            }
        }
        return dp[0][1];
    }
}
//4.space optimization
import java.util.Arrays;

public class Solution {
    public static int maximumProfit(int[] prices, int n, int fee) {
        // Write your code here.
        int[] ahead = new int[2];
        int[] cur = new int[2];
        for(int i = n - 1; i >= 0; i--)
        {
            for(int buy = 0; buy <= 1; buy++)
            {
                int profit = 0;
                if(buy == 1)
                {
                    profit = Math.max(-prices[i] + ahead[0],
                            ahead[1]);
                }
                else
                {
                    profit = Math.max((prices[i] - fee) + ahead[1],
                                ahead[0]);
                }
                cur[buy] = profit;
            }
            ahead = cur;
        }
        return cur[1];
    }
}