//https://takeuforward.org/data-structure/0-1-knapsack-dp-19/
//1.recursion
import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        return maxValue(weight, value, n - 1, maxWeight);
    }

    private static int maxValue(int[] weight, int[] value, int idx, int maxWeight)
    {
        if(idx == 0)
        {
            if(weight[idx] <= maxWeight)
                return value[idx];
            return 0;
        }
        int notTake = maxValue(weight, value, idx - 1, maxWeight);
        int take = Integer.MIN_VALUE;
        if(weight[idx] <= maxWeight)
        {
            take = value[idx] + maxValue(weight, value, idx - 1, maxWeight - weight[idx]);
        }
        return Math.max(take, notTake);
    }
}
//2.memorization
import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        int[][] dp = new int[n][maxWeight + 1];
        for(int [] row : dp)
                Arrays.fill(row, -1);
        return maxValue(weight, value, n - 1, maxWeight, dp);
    }

    private static int maxValue(int[] weight, int[] value, int idx, int maxWeight, int[][] dp)
    {
        if(idx == 0)
        {
            if(weight[idx] <= maxWeight)
                return value[idx];
            return 0;
        }
        if(dp[idx][idx] != -1)
            return dp[idx][maxWeight];
        int notTake = maxValue(weight, value, idx - 1, maxWeight, dp);
        int take = Integer.MIN_VALUE;
        if(weight[idx] <= maxWeight)
        {
            take = value[idx] + maxValue(weight, value, idx - 1, maxWeight - weight[idx], dp);
        }
        return dp[idx][maxWeight] = Math.max(take, notTake);
    }
}
//3.tabulation
import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        int[][] dp = new int[n][maxWeight + 1];
        for(int i = weight[0]; i <= maxWeight; i++)
        {
            dp[0][i] = value[0];
        }
        for(int i = 1; i < n; i++)
        {
            for(int w = 0; w <= maxWeight; w++)
            {
                int notTake = dp[i - 1][w];
                int take = Integer.MIN_VALUE;
                if(weight[i] <= w)
                    take = value[i] + dp[i - 1][w - weight[i]];
                dp[i][w] = Math.max(notTake, take);
            }
        }
        return dp[n - 1][maxWeight];
    }

}
//4.space optimization
import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        int[] prev = new int[maxWeight + 1];
        for(int i = weight[0]; i <= maxWeight; i++)
            prev[i] = value[0];
        for(int i = 1; i < n; i++)
        {
            int[] cur = new int[maxWeight + 1];
            for(int w = 0; w <= maxWeight; w++)
            {
                int notTake = prev[w];
                int take = Integer.MIN_VALUE;
                if(weight[i] <= w)
                    take = value[i] + prev[w - weight[i]];
                cur[w] = Math.max(notTake, take);
            }
            prev = cur;
        }
        return prev[maxWeight];
    }
}
//5. 1D space optimization
import java.util.* ;
import java.io.*; 

public class Solution{
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        int[] prev = new int[maxWeight + 1];
        for(int i = weight[0]; i <= maxWeight; i++)
            prev[i] = value[0];
        for(int i = 1; i < n; i++)
        {
            for(int w = maxWeight; w >= 0; w--)
            {
                int notTake = prev[w];
                int take = Integer.MIN_VALUE;
                if(weight[i] <= w)
                    take = value[i] + prev[w - weight[i]];
                prev[w] = Math.max(notTake, take);
            }
        }
        return prev[maxWeight];
    }
}