//https://www.codingninjas.com/studio/problems/triangle_1229398?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        // Write your code here.
        return minPathSum(0, 0, triangle, n);
    }
    private static int minPathSum(int i, int j, int[][] triangle, int n)
    {
        if(i == n - 1)
            return triangle[n - 1][j];
        int down = triangle[i][j] + minPathSum(i + 1, j, triangle, n);
        int diag = triangle[i][j] + minPathSum(i + 1, j + 1, triangle, n);

        return Math.min(down, diag);
    }
}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        // Write your code here.
       
        int[][] dp = new int[n][n];
        for(int[] row:dp)
            Arrays.fill(row, -1);
        return minPathSum(0, 0, triangle, n, dp);
    }
    private static int minPathSum(int i, int j, int[][] triangle, int n, int[][] dp)
    {
        if(dp[i][j] != -1)
            return dp[i][j];
        if(i == n - 1)
            return triangle[i][j];
        int down = triangle[i][j] + minPathSum(i + 1, j, triangle, n, dp);
        int diag = triangle[i][j] + minPathSum(i + 1, j + 1, triangle, n, dp);

        return dp[i][j] = Math.min(down, diag);
    }
}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        // Write your code here.
       
        int[][] dp = new int[n][n];
        for(int j = 0; j < n; j++)
            dp[n - 1][j] = triangle[n - 1][j];
        for(int i = n - 2; i >= 0; i--)
        {
            for(int j = i; j >= 0; j--)
            {
               int up = triangle[i][j] + dp[i + 1][j];
               int diag = triangle[i][j] + dp[i + 1][j + 1];
               dp[i][j] = Math.min(up, diag);
            }
        }
        return dp[0][0];
    }
}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minimumPathSum(int[][] triangle, int n) {
        // Write your code here.
       
        int[] next = new int[n];
        for(int j = 0; j < n; j++)
            next[j] = triangle[n - 1][j];
        for(int i = n - 2; i >= 0; i--)
        {
            int[] cur = new int[n];
            for(int j = i; j >= 0; j--)
            {
               int up = triangle[i][j] + next[j];
               int diag = triangle[i][j] + next[j + 1];
               cur[j] = Math.min(up, diag);
            }
            next = cur;
        }
        return next[0];
    }
}