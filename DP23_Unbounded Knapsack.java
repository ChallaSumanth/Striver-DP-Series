//https://takeuforward.org/data-structure/unbounded-knapsack-dp-23/
//1.recursion
public class Solution {
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.
        return maxProfit(n - 1, w, profit, weight);
    }

    private static int maxProfit(int idx, int w, int[] profit, int[] weight)
    {
        if(idx == 0)
        {
            return (w / weight[0]) * profit[0];
        }

        int notTake = maxProfit(idx - 1, w, profit, weight);
        int take = (int)-1e9;
        if(weight[idx] <= w)
            take = profit[idx] + maxProfit(idx, w - weight[idx], profit, weight);
        return Math.max(notTake, take);
    }
}
//2.memorization
import java.util.*;
public class Solution {
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.
        int[][] dp = new int[n][w + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return maxProfit(n - 1, w, profit, weight, dp);
    }

    private static int maxProfit(int idx, int w, int[] profit, int[] weight, int[][] dp)
    {
        if(idx == 0)
        {
            return (w / weight[0]) * profit[0];
        }
        if(dp[idx][w] != -1)
            return dp[idx][w];
        int notTake = maxProfit(idx - 1, w, profit, weight, dp);
        int take = (int)-1e9;
        if(weight[idx] <= w)
            take = profit[idx] + maxProfit(idx, w - weight[idx], profit, weight, dp);
        return dp[idx][w] = Math.max(notTake, take);
    }
}
//3.tabulation
import java.util.*;
public class Solution {
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.
        int[][] dp = new int[n][w + 1];
        for(int i = weight[0]; i <= w; i++)
            dp[0][i] = ((int)i / weight[0]) * profit[0];
        for(int i = 1; i < n; i++)
        {
            for(int wt = 0; wt <= w; wt++)
            {
                int notTake = dp[i - 1][wt];
                int take = (int)-1e9;
                if(weight[i] <= wt)
                    take = profit[i] + dp[i][wt - weight[i]];
                dp[i][wt] = Math.max(take, notTake);
            }
        }
        return dp[n - 1][w];
    }
}
//4.space optimization
import java.util.*;
public class Solution {
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.
        int[] prev = new int[w + 1];
        for(int i = weight[0]; i <= w; i++)
            prev[i] = ((int)i / weight[0]) * profit[0];
        for(int i = 1; i < n; i++)
        {
            int[] cur = new int[w + 1];
            for(int wt = 0; wt <= w; wt++)
            {
                int notTake = prev[wt];
                int take = (int)-1e9;
                if(weight[i] <= wt)
                    take = profit[i] + cur[wt - weight[i]];
                cur[wt] = Math.max(take, notTake);
            }
            prev = cur;
        }
        return prev[w];
    }
}
