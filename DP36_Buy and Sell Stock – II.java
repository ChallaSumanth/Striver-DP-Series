//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
//1.recursion
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        return maxPrft(0, len, prices, 1);
    }
    private int maxPrft(int idx, int n, int[] prices, int buy)
    {
        if(idx == n)
            return 0;
        int profit = 0;
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] + maxPrft(idx + 1, n, prices, 0),
                            maxPrft(idx + 1, n, prices, 1));
        }
        else
        {
            profit = Math.max(prices[idx] + maxPrft(idx + 1, n, prices, 1),
                                maxPrft(idx + 1, n, prices, 0));
        }
        return profit;
    }
}
//2.memorization
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return maxPrft(0, len, prices, 1, dp);
    }
    private int maxPrft(int idx, int n, int[] prices, int buy, int[][] dp)
    {
        if(idx == n)
            return 0;
        int profit = 0;
        if(dp[idx][buy] != -1)
            return dp[idx][buy];
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] + maxPrft(idx + 1, n, prices, 0, dp),
                            maxPrft(idx + 1, n, prices, 1, dp));
        }
        else
        {
            profit = Math.max(prices[idx] + maxPrft(idx + 1, n, prices, 1, dp),
                                maxPrft(idx + 1, n, prices, 0, dp));
        }
        return dp[idx][buy] = profit;
    }
}
//3.tabulation
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len + 1][2];
        for(int i = len - 1; i >= 0; i--)
        {
            int profit = 0;
            for(int buy = 0; buy <= 1; buy++)
            {
                if(buy == 1)
                {
                    profit = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
                }
                else
                {
                    profit = Math.max(prices[i] + dp[i + 1][1], dp[i + 1][0]);
                }
                dp[i][buy] = profit;
            }
        }
        return dp[0][1];
    }
    private int maxPrft(int idx, int n, int[] prices, int buy, int[][] dp)
    {
        if(idx == n)
            return 0;
        int profit = 0;
        if(dp[idx][buy] != -1)
            return dp[idx][buy];
        if(buy == 1)
        {
            profit = Math.max(-prices[idx] + maxPrft(idx + 1, n, prices, 0, dp),
                            maxPrft(idx + 1, n, prices, 1, dp));
        }
        else
        {
            profit = Math.max(prices[idx] + maxPrft(idx + 1, n, prices, 1, dp),
                                maxPrft(idx + 1, n, prices, 0, dp));
        }
        return dp[idx][buy] = profit;
    }
}
//4.space optimization
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[] prev = new int[2];
        int[] cur = new int[2];
        for(int i = len - 1; i >= 0; i--)
        {
            int profit = 0;
            for(int buy = 0; buy <= 1; buy++)
            {
                if(buy == 1)
                {
                    profit = Math.max(-prices[i] + prev[0], prev[1]);
                }
                else
                {
                    profit = Math.max(prices[i] + prev[1], prev[0]);
                }
                cur[buy] = profit;
            }
            prev = cur;
        }
        return prev[1];
    }
}
