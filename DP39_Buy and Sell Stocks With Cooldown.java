//https://www.codingninjas.com/studio/problems/best-time-to-buy-and-sell-stock-with-cooldown_3125969?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION
//1.recursion
public class Solution {
    public static int stockProfit(int[] prices) {
        // Write your code here.
        int len = prices.length;
        return maxProfit(0, 1, prices, len);
    }
    private static int maxProfit(int idx, int buy, int[] prices, int len)
    {
        if(idx >= len)
            return 0;
        int profit = 0;
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] +maxProfit(idx + 1, 0, prices, len),
                                maxProfit(idx + 1, 1, prices, len));
        }
        else
        {
            profit = Math.max(prices[idx] + maxProfit(idx + 2, 1, prices, len),
                                maxProfit(idx + 1, 0, prices, len));
        }
        return profit;
    }
}
//2.memorization
import java.util.*;
public class Solution {
    public static int stockProfit(int[] prices) {
        // Write your code here.
        int len = prices.length;
        int[][] dp = new int[len + 1][2];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return maxProfit(0, 1, prices, len, dp);
    }
    private static int maxProfit(int idx, int buy, int[] prices, int len, int[][] dp)
    {
        if(idx >= len)
            return 0;
        int profit = 0;
        if(dp[idx][buy] != -1)
            return dp[idx][buy];
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] +maxProfit(idx + 1, 0, prices, len, dp),
                                maxProfit(idx + 1, 1, prices, len, dp));
        }
        else
        {
            profit = Math.max(prices[idx] + maxProfit(idx + 2, 1, prices, len, dp),
                                maxProfit(idx + 1, 0, prices, len, dp));
        }
        return dp[idx][buy] = profit;
    }
}
//3.tabulation
import java.util.*;
public class Solution {
    public static int stockProfit(int[] prices) {
        // Write your code here.
        int n = prices.length;
        int[][] dp = new int[n + 2][2];
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
                    profit = Math.max(prices[i] + dp[i + 2][1],
                                    dp[i + 1][0]);
                }
                dp[i][buy] = profit;
            }
        }
        return dp[0][1];
    }
}
//4.space optimization
import java.util.*;
public class Solution {
    public static int stockProfit(int[] prices) {
        // Write your code here.
        int n = prices.length;
        int[] front1 = new int[2];
        int[] front2 = new int[2];
        for(int i = n - 1; i >= 0; i--)
        {
            int[] cur = new int[2];
            for(int buy = 0; buy <= 1; buy++)
            {
                int profit = 0;
                if(buy == 1)
                {
                    profit = Math.max(-prices[i] + front1[0], 
                                        front1[1]);
                }
                else
                {
                    profit = Math.max(prices[i] + front2[1],
                                    front1[0]);
                }
                cur[buy] = profit;
            }
            front2 = front1;
            front1 = cur;
        }
        return front1[1];
    }
}