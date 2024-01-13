//https://www.codingninjas.com/studio/problems/target-sum_4127362?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//same as DP18
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int targetSum(int n, int target, int[] arr) {
    	// Write your code here.
        int totalSum = 0;
        for(int num : arr)
            totalSum += num;
        if(totalSum - target < 0 || (totalSum - target) % 2 == 1)
            return 0;
        int t = (totalSum - target) / 2;
        return targetCount(n - 1, t, arr);
    }
    private static int targetCount(int idx, int target, int[] arr)
    {
        if(idx == 0)
        {
            if(target == 0 && 0 == arr[idx])
                return 2;
            if(target == 0 || arr[idx] == target)
                return 1;
            return 0;
        }
        int notPick = targetCount(idx - 1, target, arr);
        int pick = 0;
        if(arr[idx] <= target)
            pick = targetCount(idx - 1, target - arr[idx], arr);
        return pick + notPick;
    }
}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int targetSum(int n, int target, int[] arr) {
    	// Write your code here.
        int totalSum = 0;
        for(int num : arr)
            totalSum += num;
        if(totalSum - target < 0 || (totalSum - target) % 2 == 1)
            return 0;
        int t = (totalSum - target) / 2;
        int[][] dp = new int[n][t + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return targetCount(n - 1, t, arr, dp);
    }
    private static int targetCount(int idx, int target, int[] arr, int[][]dp)
    {
        if(idx == 0)
        {
            if(target == 0 && 0 == arr[idx])
                return 2;
            if(target == 0 || arr[idx] == target)
                return 1;
            return 0;
        }
        if(dp[idx][target] != -1)
            return dp[idx][target];
        int notPick = targetCount(idx - 1, target, arr, dp);
        int pick = 0;
        if(arr[idx] <= target)
            pick = targetCount(idx - 1, target - arr[idx], arr, dp);
        return dp[idx][target] = pick + notPick;
    }
}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int targetSum(int n, int target, int[] arr) {
    	// Write your code here.
        int totalSum = 0;
        for(int num : arr)
            totalSum += num;
        if(totalSum - target < 0 || (totalSum - target) % 2 == 1)
            return 0;
        int t = (totalSum - target) / 2;
        int[][] dp = new int[n][t + 1];
        if(arr[0] == 0)
            dp[0][0] = 2;
        else
            dp[0][0] = 1;
        if(arr[0] != 0 && arr[0] <= t)
            dp[0][arr[0]] = 1;
        for(int i = 1; i < n; i++)
        {
            for(int tar = 0; tar <= t; tar++)
            {
                int notTake = dp[i - 1][tar];
                int take = 0;
                if(arr[i] <= tar)
                    take = dp[i - 1][tar - arr[i]];
                dp[i][tar] = take + notTake;
            }
        }
        return dp[n - 1][t];
    }
}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int targetSum(int n, int target, int[] arr) {
    	// Write your code here.
        int totalSum = 0;
        for(int num : arr)
            totalSum += num;
        if(totalSum - target < 0 || (totalSum - target) % 2 == 1)
            return 0;
        int t = (totalSum - target) / 2;
        int[] prev = new int[t + 1];
        if(arr[0] == 0)
            prev[0] = 2;
        else
            prev[0] = 1;
        if(arr[0] != 0 && arr[0] <= t)
            prev[arr[0]] = 1;
        for(int i = 1; i < n; i++)
        {
            int[] cur = new int[t + 1];
            for(int tar = 0; tar <= t; tar++)
            {
                int notTake = prev[tar];
                int take = 0;
                if(arr[i] <= tar)
                    take = prev[tar - arr[i]];
                cur[tar] = take + notTake;
            }
            prev = cur;
        }
        return prev[t];
    }
}