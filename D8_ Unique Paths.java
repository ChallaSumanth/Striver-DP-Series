//https://www.codingninjas.com/studio/problems/unique-paths_1081470?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int uniquePaths(int m, int n) {
		// Write your code here.
		return countPaths(m - 1, n - 1);
	}
	private static int countPaths(int rows, int cols)
	{
		if(rows < 0 || cols < 0)
			return 0;
		if(rows == 0 && cols == 0)
			return 1;
		int top = countPaths(rows - 1, cols);
		int left = countPaths(rows, cols - 1);
		return top + left;
	}
}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int uniquePaths(int m, int n) {
		// Write your code here.
		int[][] dp = new int[m][n];
		for(int[] row : dp)
			Arrays.fill(row, -1);
		return countPaths(m - 1, n - 1, dp);
	}
	private static int countPaths(int rows, int cols, int[][] dp)
	{
		if(rows < 0 || cols < 0)
			return 0;
		if(rows == 0 && cols == 0)
			return 1;
		if(dp[rows][cols] != -1)
			return dp[rows][cols];
		int top = countPaths(rows - 1, cols, dp);
		int left = countPaths(rows, cols - 1, dp);
		return dp[rows][cols] = top + left;
	}
}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int uniquePaths(int m, int n) {
		// Write your code here.
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++)
		{
			for(int j = 0; j < n; j++)
			{
				if(i == 0 || j == 0)
					dp[i][j] = 1;
				else
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		return dp[m - 1][n - 1];
	}
}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int uniquePaths(int m, int n) {
		// Write your code here.
		int[] prev = new int[n];
		for(int i = 0; i < m; i++)
		{
			int [] cur = new int[n];
			for(int j = 0; j < n; j++)
			{
				if(i == 0 && j == 0)
					cur[j] = 1;
				else
				{
					int up = 0;
					int left = 0;
					if(i > 0)
						up = prev[j];
					if(j > 0)
						left = cur[j - 1];
					cur[j] = up + left;
				}
			}
			prev = cur;
		}
		return prev[n - 1];
	}
}
