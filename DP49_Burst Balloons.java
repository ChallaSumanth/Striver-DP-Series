//https://www.codingninjas.com/studio/problems/burst-balloons_628471?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    public static int burstBalloons(int []arr){
        // Write your code here.
        int n = arr.length;
        int[] ar = new int[n + 2];
        ar[0] = 1;
        for(int i = 0; i < n; i++)
            ar[i + 1] = arr[i];
        ar[n + 1] = 1;
        return maxCoins(1, n, ar);
    }
    private static int maxCoins(int i, int j, int[] arr)
    {
        if(i > j)
            return 0;
        int max = (int)-1e9;
        for(int ind = i; ind <= j; ind++)
        {
            int cost = arr[i - 1] * arr[ind] * arr[j + 1] +
                        maxCoins(i, ind - 1, arr) +
                        maxCoins(ind + 1, j, arr);
            max = Math.max(max, cost);
        }
        return max;
    }
}
//2.memorization
import java.util.*;
public class Solution {
    public static int burstBalloons(int []arr){
        // Write your code here.
        int n = arr.length;
        int[] ar = new int[n + 2];
        ar[0] = 1;
        for(int i = 0; i < n; i++)
            ar[i + 1] = arr[i];
        ar[n + 1] = 1;
        int[][] dp = new int[n + 1][n + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return maxCoins(1, n, ar, dp);
    }
    private static int maxCoins(int i, int j, int[] arr, int[][] dp)
    {
        if(i > j)
            return 0;
        int max = (int)-1e9;
        if(dp[i][j] != -1)
            return dp[i][j];
        for(int ind = i; ind <= j; ind++)
        {
            int cost = arr[i - 1] * arr[ind] * arr[j + 1] +
                        maxCoins(i, ind - 1, arr, dp) +
                        maxCoins(ind + 1, j, arr, dp);
            max = Math.max(max, cost);
        }
        return dp[i][j] = max;
    }
}
//3.tabulation
import java.util.*;
public class Solution {
    public static int burstBalloons(int []arr){
        // Write your code here.
        int n = arr.length;
        int[] ar = new int[n + 2];
        ar[0] = 1;
        for(int i = 0; i < n; i++)
            ar[i + 1] = arr[i];
        ar[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];
       for(int i = n; i >= 1; i--)
       {
           for(int j = i; j <= n; j++)
           {
               if(i > j)
                    continue;
                int max = (int)-1e9;
                for(int ind = i; ind <= j; ind++)
                {
                    int cost = ar[i - 1] * ar[ind] * ar[j + 1] +
                                    dp[i][ind - 1] + dp[ind + 1][j];
                    max = Math.max(max, cost);
                }
                dp[i][j] = max;
           }
       }
       return dp[1][n];
    }
}