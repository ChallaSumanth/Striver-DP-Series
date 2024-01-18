//https://www.codingninjas.com/studio/problems/shortest-common-supersequence_4244493?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static String shortestSupersequence(String a, String b) {
        // Write your code here..
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j > 0)
        {
            if(a.charAt(i - 1) == b.charAt(j - 1))
            {
                sb.append(a.charAt(i - 1));
                i--;
                j--;
            }
            else if(dp[i - 1][j] > dp[i][j - 1])
            {
                sb.append(a.charAt(i - 1));
                i--;
            }
            else
            {
                sb.append(b.charAt(j - 1));
                j--;
            }
        }
        while(i > 0)
        {
            sb.append(a.charAt(i - 1));
            i--;
        }
        while(j > 0)
        {
            sb.append(b.charAt(j - 1));
            j--;
        }
        return sb.reverse().toString();
    }

}