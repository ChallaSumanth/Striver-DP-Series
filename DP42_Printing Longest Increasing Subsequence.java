//https://www.codingninjas.com/studio/problems/printing-longest-increasing-subsequence_8360670?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Solution {
    public static List< Integer > printingLongestIncreasingSubsequence(int []arr, int x) {
        // Write Your Code Here
        int n = arr.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        int last_idx = 0;
        for(int i = 0; i < n; i++)
        {
            hash[i] = i;
            for(int prev = 0; prev < i; prev++)
            {
                if(arr[i] > arr[prev] && 1 + dp[prev] > dp[i])
                {
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }
            if(dp[i] > max)
            {
                max = dp[i];
                last_idx = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(arr[last_idx]);
        while(hash[last_idx] != last_idx)
        {
            last_idx = hash[last_idx];
            ans.add(arr[last_idx]);
        }
        Collections.reverse(ans);
        return ans;
    }
}