//https://www.codingninjas.com/studio/problems/edit-distance_630420?leftPanelTabValue=SUBMISSION
//1.recursion

public class Solution {

    public static int editDistance(String str1, String str2) {
        //Your code goes here
        int m = str1.length();
        int n = str2.length();

        return minSteps(str1, m - 1, str2, n - 1);
    }

    private static int minSteps(String str1, int i, String str2, int j)
    {
        if(j < 0)
            return i + 1;
        if(i < 0)
            return j + 1;
        int del = 0;
        int replace = 0;
        int insert = 0;
        if(str1.charAt(i) == str2.charAt(j))
            return minSteps(str1, i - 1, str2, j - 1);
        else
        {
            del = 1 + minSteps(str1, i - 1, str2, j);
            replace = 1 + minSteps(str1, i - 1, str2, j - 1);
            insert = 1 + minSteps(str1, i, str2, j - 1);
        }
        return Math.min(del, Math.min(replace, insert));
    }
}
//2.memorization

import java.util.*;
public class Solution {

    public static int editDistance(String str1, String str2) {
        //Your code goes here
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return minSteps(str1, m - 1, str2, n - 1, dp);
    }

    private static int minSteps(String str1, int i, String str2, int j, int[][] dp)
    {
        if(j < 0)
            return i + 1;
        if(i < 0)
            return j + 1;
        if(dp[i][j] != -1)
            return dp[i][j];
        int del = 0;
        int replace = 0;
        int insert = 0;
        if(str1.charAt(i) == str2.charAt(j))
            return dp[i][j] = minSteps(str1, i - 1, str2, j - 1, dp);
        else
        {
            del = 1 + minSteps(str1, i - 1, str2, j, dp);
            replace = 1 + minSteps(str1, i - 1, str2, j - 1, dp);
            insert = 1 + minSteps(str1, i, str2, j - 1, dp);
        }
        return dp[i][j] = Math.min(del, Math.min(replace, insert));
    }
}
//3.tabulation
import java.util.*;
public class Solution {

    public static int editDistance(String str1, String str2) {
        //Your code goes here
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            dp[i][0] = i;
        for(int j = 0; j <= n; j++)
            dp[0][j] = j;
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                {
                    int del = 1 + dp[i - 1][j];
                    int ins = 1 + dp[i][j - 1];
                    int replace = 1 + dp[i - 1][j - 1];

                    dp[i][j] = Math.min(del, Math.min(ins, replace));
                }
            }
        }
        return dp[m][n];
    }
}
//4.space optimization

import java.util.*;
public class Solution {

    public static int editDistance(String str1, String str2) {
        //Your code goes here
        int m = str1.length();
        int n = str2.length();
        int[] prev = new int[n + 1];
        for(int j = 0; j <= n; j++)
            prev[j] = j;
        for(int i = 1; i <= m; i++)
        {
            int[] cur = new int[n + 1];
            cur[0] = i;
            for(int j = 1; j <= n; j++)
            {
                if(str1.charAt(i - 1) == str2.charAt(j - 1))
                    cur[j] = prev[j - 1];
                else
                {
                    int del = prev[j];
                    int ins = cur[j - 1];
                    int replace = prev[j - 1];

                    cur[j] = 1 + Math.min(del, Math.min(ins, replace));
                }
            }
            prev = cur;
        }
        return prev[n];
    }
}