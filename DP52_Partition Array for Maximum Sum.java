//https://www.codingninjas.com/studio/problems/partition-array-for-maximum-sum_3755255?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    public static int maximumSubarray(int arr[], int k) {
        // Write your code here..
        int n = arr.length;
        return maxSum(0, n, arr, k);
    }
    private static int maxSum(int i, int n, int[] arr, int k)
    {
        if(i == n)
            return 0;
        int len = 0, sum = 0, max = (int)-1e9, maxAns = (int)-1e9;

        for(int ind = i; ind < Math.min(i + k, n); ind++)
        {
            len++;
            max = Math.max(max, arr[ind]);
            sum = (len * max) + maxSum(ind + 1, n, arr, k);
            maxAns = Math.max(maxAns, sum);
        }
        return maxAns;
    }
}
//2.memorization
import java.util.*;
public class Solution {
    public static int maximumSubarray(int arr[], int k) {
        // Write your code here..
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return maxSum(0, n, arr, k, dp);
    }
    private static int maxSum(int i, int n, int[] arr, int k, int[] dp)
    {
        if(i == n)
            return 0;
        int len = 0, sum = 0, max = (int)-1e9, maxAns = (int)-1e9;
        if(dp[i] != -1)
            return dp[i];
        for(int ind = i; ind < Math.min(i + k, n); ind++)
        {
            len++;
            max = Math.max(max, arr[ind]);
            sum = (len * max) + maxSum(ind + 1, n, arr, k, dp);
            maxAns = Math.max(maxAns, sum);
        }
        return dp[i] = maxAns;
    }
}
//3.tabulation
import java.util.*;
public class Solution {
    public static int maximumSubarray(int arr[], int k) {
        // Write your code here..
        int n = arr.length;
        int[] dp = new int[n + 1];
        for(int i = n - 1; i >= 0; i--)
        {
            int len = 0, sum = 0, max = (int)-1e9, maxAns = (int)-1e9;
            for(int ind = i; ind < Math.min(i + k, n); ind++)
            {
                len++;
                max = Math.max(max, arr[ind]);
                sum = (len * max) + dp[ind + 1];
                maxAns = Math.max(maxAns, sum);
            }
            dp[i] = maxAns;
        }
        return dp[0];
    }
}