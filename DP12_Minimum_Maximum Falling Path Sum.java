//https://www.codingninjas.com/studio/problems/maximum-path-sum-in-the-matrix_797998?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
//1.recusion
import java.util.* ;
import java.io.*; 

public class Solution {
	public static int getMaxPathSum(int[][] matrix) {
		// Write your code here
		int n = matrix.length;
		int m = matrix[0].length;
		int max = Integer.MIN_VALUE;
		for(int j = 0; j < m; j++)
		{
			int curMax = maxPathSum(n - 1, j, m, matrix);
			max = Math.max(max, curMax);
		}
		return max;
	}
	private static int maxPathSum(int i, int j, int cols, int[][] matrix)
	{
		if(j < 0 || j>= cols)
			return (int)-1e9;
		if(i == 0)
			return matrix[i][j];
		int up = matrix[i][j] + maxPathSum(i - 1, j, cols, matrix);
		int leftDiag = matrix[i][j] + maxPathSum(i - 1, j - 1, cols, matrix);
		int rightDiag = matrix[i][j] + maxPathSum(i - 1, j + 1, cols, matrix);

		return Math.max(up, Math.max(leftDiag, rightDiag));	
	}
}
//2.memorization
import java.util.* ;
import java.io.*; 

public class Solution {
	public static int getMaxPathSum(int[][] matrix) {
		// Write your code here
		int n = matrix.length;
		int m = matrix[0].length;
		int max = Integer.MIN_VALUE;
		int[][] dp = new int[n][m];
		for(int [] row : dp)
			Arrays.fill(row, -1);
		for(int j = 0; j < m; j++)
		{
			int curMax = maxPathSum(n - 1, j, m, matrix, dp);
			max = Math.max(max, curMax);
		}
		return max;
	}
	private static int maxPathSum(int i, int j, int cols, int[][] matrix, int[][] dp)
	{
		if(j < 0 || j>= cols)
			return (int)-1e9;
		if(i == 0)
			return dp[i][j] = matrix[i][j];
		int up = matrix[i][j] + maxPathSum(i - 1, j, cols, matrix, dp);
		int leftDiag = matrix[i][j] + maxPathSum(i - 1, j - 1, cols, matrix, dp);
		int rightDiag = matrix[i][j] + maxPathSum(i - 1, j + 1, cols, matrix, dp);

		return dp[i][j] = Math.max(up, Math.max(leftDiag, rightDiag));	
	}
}
//3.tabulation
import java.util.* ;
import java.io.*; 

public class Solution {
	public static int getMaxPathSum(int[][] matrix) {
		// Write your code here
		int n = matrix.length;
		int m = matrix[0].length;
		int max = Integer.MIN_VALUE;
		int[][] dp = new int[n][m];
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < m; j++)
			{
				if(i == 0)
				{
					dp[i][j] = matrix[i][j];
				}
				else
				{
					int up = matrix[i][j] + dp[i - 1][j];
					int leftDiag = (int)-1e9;
					int rightDiag = (int)-1e9;
					if(j > 0)
					{
						leftDiag = matrix[i][j] + dp[i - 1][j - 1];
					}
					if(j + 1 < m)
					{
						rightDiag = matrix[i][j] + dp[i - 1][j + 1];
					}
					dp[i][j] = Math.max(up, Math.max(leftDiag, rightDiag));
				}
			}
		}
		for(int j = 0; j < m; j++)
			max = Math.max(max, dp[n - 1][j]);
		return max;
	}
}
//4.space optimization
import java.util.* ;
import java.io.*; 

public class Solution {
	public static int getMaxPathSum(int[][] matrix) {
		// Write your code here
		int n = matrix.length;
		int m = matrix[0].length;
		int max = Integer.MIN_VALUE;
		int[] prev = new int[m];
		for(int i = 0; i < n; i++)
		{
			int[] cur = new int[m];
			for(int j = 0; j < m; j++)
			{
				if(i == 0)
				{
					cur[j] = matrix[i][j];
				}
				else
				{
					int up = matrix[i][j] + prev[j];
					int leftDiag = (int)-1e9;
					int rightDiag = (int)-1e9;
					if(j > 0)
					{
						leftDiag = matrix[i][j] + prev[j - 1];
					}
					if(j + 1 < m)
					{
						rightDiag = matrix[i][j] + prev[j + 1];
					}
					cur[j] = Math.max(up, Math.max(leftDiag, rightDiag));
				}
			}
			prev = cur;
		}
		for(int j = 0; j < m; j++)
			max = Math.max(max, prev[j]);
		return max;
	}
}
