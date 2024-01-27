//https://www.codingninjas.com/studio/problems/palindrome-partitioning-ll_873266?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    public static int palindromePartitioning(String str) {
        // Write your code here
        int n = str.length();

        return minPartitions(0, n, str) - 1;
    }
    private static int minPartitions(int i, int n, String str)
    {
        if(i == n)
            return 0;
        int min = (int)1e9;
        for(int j = i; j < n; j++)
        {
            if(isPalindrome(i, j, str))
            {
                int cost = 1 + minPartitions(j + 1, n, str);
                min = Math.min(min, cost);
            }
        }
        return min;
    }

    private static boolean isPalindrome(int i, int j, String str)
    {
        while(i < j)
        {
            if(str.charAt(i) != str.charAt(j))
                return false;
            i++;j--;
        }
        return true;
    }
}
//2.memorization
import java.util.*;
public class Solution {
    public static int palindromePartitioning(String str) {
        // Write your code here
        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return minPartitions(0, n, str, dp) - 1;
    }
    private static int minPartitions(int i, int n, String str, int[][] dp)
    {
        if(i == n)
            return 0;
        int min = (int)1e9;
        if(dp[i][n] != -1)
            return dp[i][n];
        for(int j = i; j < n; j++)
        {
            if(isPalindrome(i, j, str))
            {
                int cost = 1 + minPartitions(j + 1, n, str, dp);
                min = Math.min(min, cost);
            }
        }
        return dp[i][n] = min;
    }

    private static boolean isPalindrome(int i, int j, String str)
    {
        while(i < j)
        {
            if(str.charAt(i) != str.charAt(j))
                return false;
            i++;j--;
        }
        return true;
    }
}
//3.tabulation
import java.util.*;
public class Solution {
    public static int palindromePartitioning(String str) {
        // Write your code here
        int n = str.length();
        int[] dp = new int[n + 1];
       for(int i = n - 1; i >= 0; i--)
       {
           int min = (int)1e9;
           for(int j = i; j < n; j++)
           {
               if(isPalindrome(i, j, str))
                {
                    int cost = 1 + dp[j + 1];
                    min = Math.min(min, cost);
                }
           }
           dp[i] = min;
       }
       return dp[0] - 1;
    }
    private static boolean isPalindrome(int i, int j, String str)
    {
        while(i < j)
        {
            if(str.charAt(i) != str.charAt(j))
                return false;
            i++;j--;
        }
        return true;
    }
}