//https://www.codingninjas.com/studio/problems/longest-string-chain_3752111?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
import java.util.*;
public class Solution {
    public static int longestStrChain(String[] arr) {
        // Write your code here.
        Arrays.sort(arr, (a, b) -> a.length() - b.length());
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(compareIfPossible(arr[i], arr[j]) && 1 + dp[j] > dp[i])
                    dp[i] = 1 + dp[j];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    private static boolean compareIfPossible(String s1, String s2)
    {
        if(s1.length() - s2.length() != 1)
            return false;
        int i = 0;
        int j = 0;
        while(i < s1.length())
        {
            if(j < s2.length() && s1.charAt(i) == s2.charAt(j))
            {
                i++;
                j++;
            }
            else
                i++;
        }
        return i == s1.length() && j == s2.length();
    }
}