//https://www.codingninjas.com/studio/problems/longest-common-substring_1235207?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.tabulation
public class Solution {
    public static int lcs(String str1, String str2){
        // Write your code here.
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
   
}
//2,space optimization
public class Solution {
    public static int lcs(String str1, String str2){
        // Write your code here.
        int m = str1.length();
        int n = str2.length();
        int[] prev = new int[n + 1];
        int max = 0;
        for(int i = 1; i <= m; i++)
        {
            int[] cur = new int[n + 1];
            for(int j = 1; j <= n; j++)
            {
                if(str1.charAt(i - 1) == str2.charAt(j - 1))
                    cur[j] = 1 + prev[j - 1];
                max = Math.max(max, cur[j]);
            }
            prev = cur;
        }
        return max;
    }
   
}