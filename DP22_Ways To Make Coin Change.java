//https://www.codingninjas.com/studio/problems/ways-to-make-coin-change_630471?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
//1.recursion
public class Solution {

	public static long countWaysToMakeChange(int denominations[], int value){
        //write your code here			
		int n = denominations.length;
		return countWays(n - 1, denominations, value);
	}

	private static long countWays(int idx, int[] denominations, int value)
	{
		if(idx == 0)
		{
			if(value % denominations[0] == 0)
				return 1;
			return 0;
		}
		long notTake = countWays(idx - 1, denominations, value);
		long take = 0;
		if(denominations[idx] <= value)
			take = countWays(idx, denominations, value - denominations[idx]);
		return take + notTake;
	}
	
}
//2.memorization
import java.util.Arrays;

public class Solution {

	public static long countWaysToMakeChange(int denominations[], int value){
        //write your code here			
		int n = denominations.length;
		long[][] dp = new long[n][value + 1];
		for(long[] row : dp)
			Arrays.fill(row, -1);
		return countWays(n - 1, denominations, value, dp);
	}

	private static long countWays(int idx, int[] denominations, int value, long[][] dp)
	{
		if(idx == 0)
		{
			if(value % denominations[0] == 0)
				return 1;
			return 0;
		}
		if(dp[idx][value] != -1)
			return dp[idx][value];
		long notTake = countWays(idx - 1, denominations, value, dp);
		long take = 0;
		if(denominations[idx] <= value)
			take = countWays(idx, denominations, value - denominations[idx], dp);
		return dp[idx][value] = take + notTake;
	}
	
}
//3.tabulation
import java.util.Arrays;

public class Solution {

	public static long countWaysToMakeChange(int denominations[], int value){
        //write your code here			
		int n = denominations.length;
		long[][] dp = new long[n][value + 1];
		for(int i = 0; i <= value; i++)
		{
			if(i % denominations[0] == 0)
				dp[0][i] = 1;
		}
		for(int i = 1; i < n; i++)
		{
			for(int den = 0; den <= value; den++)
			{
				long notTake = dp[i - 1][den];
				long take = 0;
				if(denominations[i] <= den)
					take = dp[i][den - denominations[i]];
				dp[i][den] = take + notTake;
			}
		}
		return dp[n - 1][value];
	}	
}
//4.space optimization
import java.util.Arrays;

public class Solution {

	public static long countWaysToMakeChange(int denominations[], int value){
        //write your code here			
		int n = denominations.length;
		long[] prev = new long[value + 1];
		for(int i = 0; i <= value; i++)
		{
			if(i % denominations[0] == 0)
				prev[i] = 1;
		}
		for(int i = 1; i < n; i++)
		{
			long[] cur = new long[value + 1];
			for(int den = 0; den <= value; den++)
			{
				long notTake = prev[den];
				long take = 0;
				if(denominations[i] <= den)
					take = cur[den - denominations[i]];
				cur[den] = take + notTake;
			}
			prev = cur;
		}
		return prev[value];
	}	
}