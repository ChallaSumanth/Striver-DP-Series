//https://www.codingninjas.com/studio/problems/chocolate-pickup_3125885?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int maximumChocolates(int r, int c, int[][] grid) {
		// Write your code here.
		return maximumCollection(0, 0, c - 1, r, c, grid);
	}
	private static int maximumCollection(int i, int j1, int j2, int rows, int cols, int[][] grid)
	{
		if(j1 < 0 || j1 >= cols || j2 < 0 || j2 >= cols)
			return (int)-1e9;
		if(i == rows - 1)
		{
			if(j1 == j2)
			{
				return grid[i][j1];
			}
			else
			{
				return grid[i][j1] + grid[i][j2];
			}
		}
		int maxCol = 0;
		for(int d1 = -1; d1 <= 1; d1++)
		{
			for(int d2 = -1; d2 <= 1; d2++)
			{
				int ans = 0;
				if(j1 == j2)
				{
					ans = grid[i][j1] +  maximumCollection(i + 1, j1 + d1, j2 + d2, rows, cols, grid);
				}
				else
				{
					ans = grid[i][j1] + grid[i][j2] + maximumCollection(i + 1, j1 + d1, j2 + d2, rows, cols, grid);
				}
				maxCol = Math.max(maxCol, ans);
			}
		}
		return maxCol;
	}
}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int maximumChocolates(int r, int c, int[][] grid) {
		// Write your code here.
		int[][][] dp = new int[r][c][c];
		for(int[][] arr : dp)
			for(int[] row : arr)
				Arrays.fill(row, -1);
		return maximumCollection(0, 0, c - 1, r, c, grid, dp);
	}
	private static int maximumCollection(int i, int j1, int j2, int rows, int cols, int[][] grid, int[][][] dp)
	{
		if(j1 < 0 || j1 >= cols || j2 < 0 || j2 >= cols)
			return (int)-1e9;
		if(dp[i][j1][j2] != -1)
			return dp[i][j1][j2];
		if(i == rows - 1)
		{
			if(j1 == j2)
			{
				return grid[i][j1];
			}
			else
			{
				return grid[i][j1] + grid[i][j2];
			}
		}
		int maxCol = 0;
		for(int d1 = -1; d1 <= 1; d1++)
		{
			for(int d2 = -1; d2 <= 1; d2++)
			{
				int ans = 0;
				if(j1 == j2)
				{
					ans = grid[i][j1] +  maximumCollection(i + 1, j1 + d1, j2 + d2, rows, cols, grid, dp);
				}
				else
				{
					ans = grid[i][j1] + grid[i][j2] + maximumCollection(i + 1, j1 + d1, j2 + d2, rows, cols, grid, dp);
				}
				maxCol = Math.max(maxCol, ans);
			}
		}
		return dp[i][j1][j2] = maxCol;
	}
}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int maximumChocolates(int r, int c, int[][] grid) {
		// Write your code here.
		int[][][] dp = new int[r][c][c];
		
		for(int j1 = 0; j1 < c; j1++)
		{
			for(int j2 = 0; j2 < c; j2++)
			{
				if(j1 == j2)
				{
					dp[r - 1][j1][j2] = grid[r - 1][j1];
				}
				else
				{
					dp[r - 1][j1][j2] = grid[r - 1][j1] + grid[r - 1][j2]; 
				}
			}
		}

		for(int i = r - 2; i >= 0; i--)
		{
			for(int j1 = 0; j1 < c; j1++)
			{
				for(int j2 = 0; j2 < c; j2++)
				{
					int maxCol = (int)-1e9;
					
					for(int d1 = -1; d1 <= 1; d1++)
					{
						for(int d2 = -1; d2 <= 1; d2++)
						{
							int ans = 0;

							if(j1 == j2)
								ans = grid[i][j1];
							else
								ans = grid[i][j1] + grid[i][j2];
					
							if((j1 + d1) < 0 || (j1 + d1) >= c || ((j2 + d2) < 0) || (j2 + d2) >= c)
								ans += (int)-1e9;
							else
								ans += dp[i + 1][j1 + d1][j2 + d2];
							maxCol = Math.max(maxCol, ans);
						}
					}
					dp[i][j1][j2] = maxCol;
				}
			}
		}

		return dp[0][0][c - 1];
	}
}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int maximumChocolates(int r, int c, int[][] grid) {
		// Write your code here.
		int[][] cur = new int[c][c];
		int[][] front = new int[c][c];

		for(int j1 = 0; j1 < c; j1++)
		{
			for(int j2 = 0; j2 < c; j2++)
			{
				 if(j1 == j2)
				 	front[j1][j2] = grid[r - 1][j1];
				 else
				 	front[j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
			}
		}
		for(int i = r - 2; i >= 0; i--)
		{
			for(int j1 = 0; j1 < c; j1++)
			{
				for(int j2 = 0; j2 < c; j2++)
				{
					int maxCol = (int)-1e9;

					for(int d1 = -1; d1 <= 1; d1++)
					{
						for(int d2 = -1; d2 <= 1; d2++)
						{
							int ans = 0;
							if(j1 == j2)
								ans = grid[i][j2];
							else
								ans = grid[i][j1] + grid[i][j2];
							
							if((j1 + d1) < 0 || (j1 + d1) >= c || (j2 + d2) < 0 || (j2 + d2) >= c)
							{
								ans += (int)-1e9;
							}
							else
							{
								ans += front[j1 + d1][j2 + d2];
							}
							maxCol = Math.max(maxCol, ans);
						}
					}
					cur[j1][j2] = maxCol;
				}
			}
			for(int a = 0; a < c; a++)
				front[a] = cur[a].clone();
		}
		return front[0][c - 1];
	}
}