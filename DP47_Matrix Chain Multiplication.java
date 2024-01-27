//https://www.codingninjas.com/studio/problems/matrix-chain-multiplication_624880?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION
//1.recursion
public class Solution {

	
	public static int mcm(int[] p){
		int n = p.length;
		return minMul(1, n - 1, p);
	}

	private static int minMul(int i, int j, int[] p)
	{
		if(i == j)
			return 0;
		int minSteps = (int)1e9;;
		for(int k = i; k <= j - 1; k++)
		{
			int steps = p[i - 1] * p[k] * p[j] +
				minMul(i, k, p) + minMul(k + 1, j, p);
			minSteps = Math.min(minSteps, steps);
		}

		return minSteps;
	}
}
//2.memorization
import java.util.*;
public class Solution {

	
	public static int mcm(int[] p){
		
		int n = p.length;
		int[][] dp = new int[n][n];
		for(int[] row : dp)
			Arrays.fill(row, -1);
		return minMul(1, n - 1, p, dp);
	}

	private static int minMul(int i, int j, int[] p, int[][] dp)
	{
		if(i == j)
			return 0;
		int minSteps = (int)1e9;
		if(dp[i][j] != -1)
			return dp[i][j];
		for(int k = i; k <= j - 1; k++)
		{
			int steps = p[i - 1] * p[k] * p[j] +
				minMul(i, k, p, dp) + minMul(k + 1, j, p, dp);
			minSteps = Math.min(minSteps, steps);
		}

		return dp[i][j] = minSteps;
	}	
}
//3.tabulation
import java.util.*;
public class Solution {
	public static int mcm(int[] p){
		
		int n = p.length;
		int[][] dp = new int[n][n];
		for(int i = 1; i < n; i++)
			dp[i][i] = 0;
		for(int i = n - 1; i >= 1; i--)
		{
			for(int j = i + 1; j < n; j++)
			{
				int minSteps = (int)1e9;
				for(int k = i; k <= j - 1; k++)
				{
					int steps = p[i - 1] * p[k] * p[j] +
						dp[i][k] + dp[k + 1][j];
					minSteps = Math.min(minSteps, steps);
				}
				dp[i][j] = minSteps;
			}
		}
		return dp[1][n - 1];
	}
}