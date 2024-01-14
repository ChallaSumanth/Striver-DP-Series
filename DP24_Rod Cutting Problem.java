//https://www.codingninjas.com/studio/problems/rod-cutting-problem_800284?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
	public static int cutRod(int price[], int n) {
		// Write your code here.
		return maxCost(n - 1, n, price);
	}

	private static int maxCost(int idx, int n, int[] price)
	{
		if(idx == 0)
			return n * price[0];
		
		int notTake = maxCost(idx - 1, n, price);
		int take = (int)-1e9;
		if(idx + 1 <= n)
			take = price[idx] + maxCost(idx, n - (idx + 1), price);
		return Math.max(take, notTake);
	}
}
//2.memorization
import java.util.Arrays;

public class Solution {
	public static int cutRod(int price[], int n) {
		// Write your code here.
		int[][] dp = new int[n][n + 1];
		for(int[] row : dp)
			Arrays.fill(row, -1);
		return maxCost(n - 1, n, price, dp);
	}

	private static int maxCost(int idx, int n, int[] price, int[][] dp)
	{
		if(idx == 0)
			return n * price[0];
		if(dp[idx][n] != -1)
			return dp[idx][n];
		int notTake = maxCost(idx - 1, n, price, dp);
		int take = (int)-1e9;
		if(idx + 1 <= n)
			take = price[idx] + maxCost(idx, n - (idx + 1), price, dp);
		return dp[idx][n] = Math.max(take, notTake);
	}
}
//3.tabulation
import java.util.Arrays;

public class Solution {
	public static int cutRod(int price[], int n) {
		// Write your code here.
		int[][] dp = new int[n][n + 1];
		for(int i = 0; i <= n; i++)
			dp[0][i] = i * price[0];
		for(int i = 1; i < n; i++)
		{
			for(int len = 0; len <= n; len++)
			{
				int notTake = dp[i - 1][len];
				int take = (int)-1e9;
				if(i + 1 <= len)
					take = price[i] + dp[i][len - (i + 1)];
				dp[i][len] = Math.max(notTake, take);
			}
		}
		return dp[n - 1][n];
	}

}
//4.space optimization
import java.util.Arrays;

public class Solution {
	public static int cutRod(int price[], int n) {
		// Write your code here.
		int[] prev = new int[n + 1];
		for(int i = 0; i <= n; i++)
			prev[i] = i * price[0];
		for(int i = 1; i < n; i++)
		{
			int[] cur = new int[n + 1];
			for(int len = 0; len <= n; len++)
			{
				int notTake = prev[len];
				int take = (int)-1e9;
				if(i + 1 <= len)
					take = price[i] + cur[len - (i + 1)];
				cur[len] = Math.max(notTake, take);
			}
			prev = cur;
		}
		return prev[n];
	}

}