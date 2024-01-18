//https://www.codingninjas.com/studio/problems/subsequence-counting_3755256?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    public static int distinctSubsequences(String str, String sub) {
        // Write your code here.
        int m = str.length();
        int n = sub.length();
        return distinctCount(str, m - 1, sub, n - 1);
    }

    private static int distinctCount(String str, int i, String sub, int j)
    {
        if(j < 0)
            return 1;
        if(i < 0)
            return 0;
        if(str.charAt(i) == sub.charAt(j))
            return distinctCount(str, i - 1, sub, j - 1) +
                    distinctCount(str, i - 1, sub, j);
        else
            return distinctCount(str, i - 1, sub, j);
    }
}
//2.memorization
import java.util.Arrays;

public class Solution {
    static int mod = (int)1e9 + 7;
    public static int distinctSubsequences(String str, String sub) {
        // Write your code here.
        int m = str.length();
        int n = sub.length();
        int[][] dp = new int[m][n];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return distinctCount(str, m - 1, sub, n - 1, dp);
    }

    private static int distinctCount(String str, int i, String sub, int j, int[][] dp)
    {
        if(j < 0)
            return 1;
        if(i < 0)
            return 0;
        if(dp[i][j] != -1)
            return dp[i][j];
        if(str.charAt(i) == sub.charAt(j))
            return dp[i][j] = distinctCount(str, i - 1, sub, j - 1, dp) % mod +
                    distinctCount(str, i - 1, sub, j, dp) % mod;
        else
            return dp[i][j] = distinctCount(str, i - 1, sub, j, dp) % mod;
    }
}
//3.Tabulation
import java.util.Arrays;

public class Solution {
    static int mod = (int)1e9 + 7;
    public static int distinctSubsequences(String str, String sub) {
        // Write your code here.
        int m = str.length();
        int n = sub.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            dp[i][0] = 1;
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(str.charAt(i - 1) == sub.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] % mod + dp[i - 1][j] % mod;
                else
                    dp[i][j] = dp[i - 1][j] % mod;
            }
        }
        return dp[m][n];   
    }
}
//4.space optimization
import java.util.Arrays;

public class Solution {
    static int mod = (int)1e9 + 7;
    public static int distinctSubsequences(String str, String sub) {
        // Write your code here.
        int m = str.length();
        int n = sub.length();
        int[] prev = new int[n + 1];
        prev[0] = 1;
        
        for(int i = 1; i <= m; i++)
        {
            int[] cur = new int[n + 1];
            cur[0] = 1;
            for(int j = 1; j <= n; j++)
            {
                if(str.charAt(i - 1) == sub.charAt(j - 1))
                    cur[j] = prev[j - 1] % mod + prev[j] % mod;
                else
                    cur[j] = prev[j] % mod;
            }
            prev = cur;
        }
        return prev[n];   
    }
}
//1.D array space optimization
import java.util.Arrays;

public class Solution {
    static int mod = (int)1e9 + 7;
    public static int distinctSubsequences(String str, String sub) {
        // Write your code here.
        int m = str.length();
        int n = sub.length();
        int[] prev = new int[n + 1];
        prev[0] = 1;
        
        for(int i = 1; i <= m; i++)
        {
            for(int j = n; j >= 1; j--)
            {
                if(str.charAt(i - 1) == sub.charAt(j - 1))
                    prev[j] = prev[j - 1] % mod + prev[j] % mod;
            }
        }
        return prev[n];   
    }
}