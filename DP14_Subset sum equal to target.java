//https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        // Write your code here.
        return subsetSumCheck(n - 1, k, arr);
    }
    private static boolean subsetSumCheck(int idx, int k, int[] arr)
    {
        if(k == 0)
            return true;
        if(idx == 0)
            return k == arr[0];
        boolean notTake = subsetSumCheck(idx - 1, k, arr);
        boolean take = false;
            if(k >= arr[idx])
                take = subsetSumCheck(idx - 1, k - arr[idx], arr);
        return notTake || take;
    }
}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        // Write your code here.
        int[][] dp = new int[n][k + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return subsetSumCheck(n - 1, k, arr, dp);
    }
    private static boolean subsetSumCheck(int idx, int k, int[] arr, int[][] dp)
    {
        if(k == 0)
            return true;
        if(idx == 0)
            return k == arr[0];
        if(dp[idx][k] != -1)
            return dp[idx][k] == 1 ? true : false;
        boolean notTake = subsetSumCheck(idx - 1, k, arr, dp);
        boolean take = false;
            if(k >= arr[idx])
                take = subsetSumCheck(idx - 1, k - arr[idx], arr, dp);

        dp[idx][k] = (notTake || take) ? 1 : 0;
        return notTake || take;
    }
}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        // Write your code here.
        boolean[][] dp = new boolean[n][k + 1];
        for(int i = 0; i < n; i++)
            dp[i][0] = true;
        if(arr[0] <= k)
            dp[0][arr[0]] = true;
        for(int i = 1; i < n; i++)
        {
            for(int target = 1; target <= k; target++)
            {
                boolean notTake = dp[i - 1][target];
                boolean take = false;
                if(target >= arr[i])
                    take = dp[i - 1][target - arr[i]];
                dp[i][target] = notTake || take;
            }
        }
        return dp[n - 1][k];
        
    }
}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        // Write your code here.
        boolean[] prev = new boolean[k + 1];
        prev[0] = true;
        if(arr[0] <= k)
            prev[arr[0]] = true;
        for(int i = 1; i < n; i++)
        {
            boolean[] cur = new boolean[k + 1];
            cur[0] = true;
            for(int target = 1; target <= k; target++)
            {
                boolean notTake = prev[target];
                boolean take = false;
                if(target >= arr[i])
                    take = prev[target - arr[i]];
                cur[target] = notTake || take;
            }
            prev = cur;
        }
        return prev[k];
        
    }
}
