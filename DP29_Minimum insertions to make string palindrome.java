//https://www.codingninjas.com/studio/problems/minimum-insertions-to-make-a-string-palindrome_985293?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
//1.tabulation
public class Solution {
    public static int minInsertion(String str) {
        // Write your code here.
        int len = str.length();
        String str1 = new StringBuilder(str).reverse().toString();
        int[][] dp = new int[len + 1][len + 1];
        for(int i = 1; i <= len; i++)
        {
            for(int j = 1; j <= len; j++)
            {
                if(str.charAt(i - 1) == str1.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return len - dp[len][len];
    }
}