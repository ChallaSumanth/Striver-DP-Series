//https://www.codingninjas.com/studio/problems/minimum-path-sum_985349?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minSumPath(int[][] grid) {
    	// Write your code here.
        int n = grid.length;
        int m = grid[0].length;
        return minSum(n - 1, m - 1, grid);
    }
    private static int minSum(int row, int col, int[][] grid)
    {
        if(row == 0 && col == 0)
            return grid[row][col];
        if(row < 0 || col < 0)
            return (int)1e9;
        int left = grid[row][col] + minSum(row, col - 1, grid); 
        int up = grid[row][col] + minSum(row - 1, col, grid);
        return Math.min(up , left);
    }
}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minSumPath(int[][] grid) {
    	// Write your code here.
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return minSum(n - 1, m - 1, grid, dp);
    }
    private static int minSum(int row, int col, int[][] grid, int[][] dp)
    {
        if(row == 0 && col == 0)
            return grid[row][col];
        if(row < 0 || col < 0)
            return (int)1e9;
        if(dp[row][col] != -1)
            return dp[row][col];
        int left = grid[row][col] + minSum(row, col - 1, grid, dp); 
        int up = grid[row][col] + minSum(row - 1, col, grid, dp);
        return dp[row][col] = Math.min(up , left);
    }
}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minSumPath(int[][] grid) {
    	// Write your code here.
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(i == 0 && j == 0)
                    dp[i][j] = grid[i][j];
                else
                {
                    int up = grid[i][j];
                    int left = grid[i][j];
                    if(i > 0)
                        up += dp[i - 1][j];
                    else
                        up += (int)1e9;
                    if(j > 0)
                        left += dp[i][j - 1];
                    else
                        left += (int)1e9;
                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int minSumPath(int[][] grid) {
    	// Write your code here.
        int n = grid.length;
        int m = grid[0].length;
        int[] prev = new int[m];
        for(int i = 0; i < n; i++)
        {
            int[] cur = new int[m];
            for(int j = 0; j < m; j++)
            {
                if(i == 0 && j == 0)
                    cur[j] = grid[i][j];
                else
                {
                    int up = grid[i][j];
                    int left = grid[i][j];
                    if(i > 0)
                        up += prev[j];
                    else
                        up += (int)1e9;
                    if(j > 0)
                        left += cur[j - 1];
                    else
                        left += (int)1e9;
                    cur[j] = Math.min(up, left);
                }
            }
            prev = cur;
        }
        return prev[m - 1];
    }
}