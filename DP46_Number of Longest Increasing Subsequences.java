//https://www.codingninjas.com/studio/problems/number-of-longest-increasing-subsequence_3751627?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION
public class Solution {
    public static int findNumberOfLIS(int []arr) {
        // Write your code here.
        int n = arr.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        int max = 1;
        for(int i = 0; i < n; i++)
        {
            dp[i] = 1;
            count[i] = 1;
            for(int prev = 0; prev < i; prev++)
            {
                if(arr[i] > arr[prev] && 1 + dp[prev] > dp[i])
                {
                    dp[i] = 1 + dp[prev];
                    count[i] = count[prev];
                }
                else if(arr[i] > arr[prev] && 1 + dp[prev] == dp[i])
                    count[i] += count[prev];
            }
            max = Math.max(max, dp[i]);
        }
        int cnt = 0;
        for(int i = 0; i < n; i++)
        {
            if(dp[i] == max)
                cnt += count[i];
        }
        return cnt;
    }
}