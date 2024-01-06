//https://www.codingninjas.com/studio/problems/unique-paths-ii_977241?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
import java.util.*;
public class Solution {
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        // Write your code here.
        return countPaths(n - 1, m - 1, mat);
    }

    private static int countPaths(int row, int col, ArrayList<ArrayList<Integer>> mat)
    {
        if(row >= 0 && col >= 0 && mat.get(row).get(col) == -1)
            return 0;
        if(row < 0 || col < 0)
            return 0;
        if(row == 0 && col == 0)
            return 1;
        int up = countPaths(row - 1, col, mat);
        int left = countPaths(row, col - 1, mat);
        return up + left;
    }

}
//2.memorization
import java.util.*;
public class Solution {
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        // Write your code here.
        int[][] dp = new int[n][m];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return countPaths(n - 1, m - 1, mat, dp);
    }

    private static int countPaths(int row, int col, ArrayList<ArrayList<Integer>> mat, int[][] dp)
    {
        if(row >= 0 && col >= 0 && mat.get(row).get(col) == -1)
            return 0;
        if(row < 0 || col < 0)
            return 0;
        if(row == 0 && col == 0)
            return 1;
        if(dp[row][col] != -1)
            return dp[row][col];
        int up = countPaths(row - 1, col, mat, dp);
        int left = countPaths(row, col - 1, mat, dp);
        return dp[row][col] = up + left;
    }

}
//3.tabulation
//https://leetcode.com/problems/unique-paths-ii/submissions/
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
         int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(i >= 0 && j >= 0 && obstacleGrid[i][j] == 1)
                {
                    dp[i][j] = 0;
                    continue;
                }
                if(i == 0 && j == 0)
                {
                    dp[i][j] = 1;
                    continue;
                }
               
                    int up = 0;
                    int left = 0;
                    if (i > 0)
                    {
                        up = dp[i - 1][j];
                    }
                    if(j > 0)
                    {
                        left = dp[i][j - 1];
                    }
                    dp[i][j] = up + left;
                
            }
        }
        return dp[n - 1][m - 1];
    }
}
//4.space optimization
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[] prev = new int[m];
        for(int i = 0; i < n; i++)
        {
            int [] cur = new int[m];
            for(int j = 0; j < m; j++)
            {
                if(i >= 0 && j >= 0 && obstacleGrid[i][j] == 1)
                {
                    cur[j] = 0;
                    continue;
                }
                if(i == 0 && j == 0)
                {
                    cur[j] = 1;
                    continue;
                }
               
                    int up = 0;
                    int left = 0;
                    if (i > 0)
                    {
                        up = prev[j];
                    }
                    if(j > 0)
                    {
                        left = cur[j - 1];
                    }
                    cur[j] = up + left;
            }
            prev = cur;
        }
        return prev[m - 1];
    }
}