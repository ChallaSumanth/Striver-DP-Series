//https://www.codingninjas.com/studio/problems/divisible-set_3754960?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
import java.util.*;
public class Solution {
    public static List< Integer > divisibleSet(int []arr) {
        // Write your code here.
        int n = arr.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        Arrays.sort(arr);
        int max = 1;
        int last_idx = 0;
        for(int i = 0; i < n; i++)
        {
            hash[i] = i;
            for(int prev = 0; prev < i; prev++)
            {
                if(arr[i] % arr[prev] == 0 && dp[i] < 1 + dp[prev])
                {
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }
            if(max < dp[i])
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