//https://www.codingninjas.com/studio/problems/minimal-cost_8180930?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    public static int minimizeCost(int n, int k, int []height){
        // Write your code here.
        return solve(n - 1, k, height);
    }
    private static int solve(int idx, int k, int[] height)
    {
        if(idx == 0)
            return 0;
        int minSteps = Integer.MAX_VALUE;
        for(int i = 1; i <= k; i++)
        {
            if(idx - i >= 0)
             {
                int step = solve(idx - i, k, height)+
                 Math.abs(height[idx] - height[idx - i]);
                minSteps = Math.min(step, minSteps);
             }
        }
        return minSteps;
    }
}
//2.Memorization
import java.util.*;
public class Solution {
    public static int minimizeCost(int n, int k, int []height){
        // Write your code here.
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return solve(n - 1, k, height, dp);
    }
    private static int solve(int idx, int k, int[] height, int[] dp)
    {
        if(idx == 0)
            return 0;
        int minSteps = Integer.MAX_VALUE;
        if(dp[idx] != -1)
            return dp[idx];
        for(int i = 1; i <= k; i++)
        {
            if(idx - i >= 0)
             {
                int step = solve(idx - i, k, height, dp)+
                 Math.abs(height[idx] - height[idx - i]);
                minSteps = Math.min(step, minSteps);
             }
        }
        return dp[idx] = minSteps;
    }
}
//3.Tabulation
import java.util.*;
public class Solution {
    public static int minimizeCost(int n, int k, int []height){
        // Write your code here.
        int[] dp = new int[n];
        for(int i = 1; i < n; i++)
        {
            int minSteps = Integer.MAX_VALUE;
            for(int j = 1; j <= k; j++)
            {
                if(i - j >= 0)
                {
                    int step = dp[i - j] + Math.abs(height[i] - height[i - j]);
                    minSteps = Math.min(step, minSteps);
                }
            }
            dp[i] = minSteps;
        }
        return dp[n - 1];
    }
    
}