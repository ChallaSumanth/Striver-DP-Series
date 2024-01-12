//https://leetcode.com/problems/coin-change/
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minimumElements(int num[], int x) {
        // Write your code here..
        int n = num.length;
        
        int ans = minCount(n - 1, num, x);
        if(ans != (int)1e9)
            return ans;
        return -1;
    }

    private static int minCount(int idx, int[] num, int x)
    {
        if(idx == 0)
        {
            if((x % num[0]) == 0)
                return (x / num[0]);
            else
                return (int)1e9;
        }

        int notTake = 0 + minCount(idx - 1, num, x);
        int take = (int)1e9;
        if(x >= num[idx])
            take = 1 + minCount(idx, num, x - num[idx]);
        return  Math.min(take, notTake);
    }

}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minimumElements(int num[], int x) {
        // Write your code here..
        int n = num.length;
        int[][] dp = new int[n][x + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        int ans = minCount(n - 1, num, x, dp);
        if(ans != (int)1e9)
            return ans;
        return -1;
    }

    private static int minCount(int idx, int[] num, int x, int[][] dp)
    {
        if(idx == 0)
        {
            if((x % num[0]) == 0)
                return (x / num[0]);
            else
                return (int)1e9;
        }
        if(dp[idx][x] != -1)
            return dp[idx][x];
        int notTake = 0 + minCount(idx - 1, num, x, dp);
        int take = (int)1e9;
        if(x >= num[idx])
            take = 1 + minCount(idx, num, x - num[idx], dp);
        return dp[idx][x] = Math.min(take, notTake);
    }

}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minimumElements(int num[], int x) {
        // Write your code here..
        int n = num.length;
        int[][] dp = new int[n][x + 1];
        for(int i = 0; i <= x; i++)
        {
            if(i % num[0] == 0)
                dp[0][i] = i / num[0];
            else 
                dp[0][i] = (int)1e9;
        }
        for(int i = 1; i < n; i++)
        {
            for(int t = 0; t <= x; t++)
            {
                int notTake = dp[i - 1][t];
                int take = (int)1e9;
                if(t >= num[i])
                    take = 1 + dp[i][t - num[i]];
                dp[i][t] = Math.min(notTake, take);
            }
        }
        return dp[n - 1][x] == (int)1e9 ? -1 : dp[n - 1][x];
    }

}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minimumElements(int num[], int x) {
        // Write your code here..
        int n = num.length;
        int[] prev = new int[x + 1];
        for(int i = 0; i <= x; i++)
        {
            if(i % num[0] == 0)
                prev[i] = i / num[0];
            else 
                prev[i] = (int)1e9;
        }
        for(int i = 1; i < n; i++)
        {
            int[] cur = new int[x + 1];
            for(int t = 0; t <= x; t++)
            {
                int notTake = prev[t];
                int take = (int)1e9;
                if(t >= num[i])
                    take = 1 + cur[t - num[i]];
                cur[t] = Math.min(notTake, take);
            }
            prev = cur;
        }
        return prev[x] == (int)1e9 ? -1 : prev[x];
    }
}
