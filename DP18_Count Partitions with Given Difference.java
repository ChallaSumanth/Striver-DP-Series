//https://www.codingninjas.com/studio/problems/partitions-with-given-difference_3751628?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int countPartitions(int n, int d, int[] arr) {
		// Write your code here.
		int total = 0;
		for(int i = 0; i < n; i++)
			total += arr[i];
		if(total - d < 0 || (total - d) % 2 == 1)
			return 0;
		int target = (total - d) / 2;
		return countWays(n - 1, arr, target);
	}
	private static int countWays(int idx, int[] arr, int target)
	{
		
		if(idx == 0)
		{
			if(target == 0 && 0 == arr[0])
				return 2;
			if(target == 0 || target == arr[0])
				return 1;
			return 0;
		}
		int notPick = countWays(idx - 1, arr, target);
		int pick = 0;
		if(arr[idx] <= target)
			pick = countWays(idx - 1, arr, target - arr[idx]);
		return pick + notPick;
	}
}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution {
	static int mod = (int)1e9 + 7;
	public static int countPartitions(int n, int d, int[] arr) {
		// Write your code here.
		int total = 0;
		for(int i = 0; i < n; i++)
			total += arr[i];
		if(total - d < 0 || (total - d) % 2 == 1)
			return 0;
		int target = (total - d) / 2;
		int[][] dp = new int[n][target + 1];
		for(int[] row : dp)
			Arrays.fill(row, -1);
		return countWays(n - 1, arr, target, dp);
	}
	private static int countWays(int idx, int[] arr, int target, int[][] dp)
	{
		
		if(idx == 0)
		{
			if(target == 0 && 0 == arr[0])
				return 2;
			if(target == 0 || target == arr[0])
				return 1;
			return 0;
		}
		if(dp[idx][target] != -1)
			return dp[idx][target];
		int notPick = countWays(idx - 1, arr, target, dp);
		int pick = 0;
		if(arr[idx] <= target)
			pick = countWays(idx - 1, arr, target - arr[idx], dp);
		return dp[idx][target] = (pick + notPick) % mod;
	}
}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
	static int mod = (int)1e9 + 7;
	public static int countPartitions(int n, int d, int[] arr) {
		// Write your code here.
		int total = 0;
		for(int i = 0; i < n; i++)
			total += arr[i];
		if(total - d < 0 || (total - d) % 2 == 1)
			return 0;
		int target = (total - d) / 2;
		int[][] dp = new int[n][target + 1];
		if(arr[0] == 0)
			dp[0][0] = 2;
		else
			dp[0][0] = 1;
		if(arr[0] != 0 && arr[0] <= target)
			dp[0][arr[0]] = 1;
		for(int i = 1; i < n; i++)
		{
			for(int t = 0; t <= target; t++)
			{
				int notPick = dp[i - 1][t];
				int pick = 0;
				if(arr[i] <= t)
					pick = dp[i - 1][t - arr[i]];
				dp[i][t] = (pick + notPick) % mod;
			}
		}
		return dp[n - 1][target];
	}
	
}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
	static int mod = (int)1e9 + 7;
	public static int countPartitions(int n, int d, int[] arr) {
		// Write your code here.
		int total = 0;
		for(int i = 0; i < n; i++)
			total += arr[i];
		if(total - d < 0 || (total - d) % 2 == 1)
			return 0;
		int target = (total - d) / 2;
		int[] prev = new int[target + 1];
		if(arr[0] == 0)
			prev[0] = 2;
		else
			prev[0] = 1;
		if(arr[0] != 0 && arr[0] <= target)
			prev[arr[0]] = 1;
		for(int i = 1; i < n; i++)
		{
			int[] cur = new int[target + 1];
			for(int t = 0; t <= target; t++)
			{
				int notPick = prev[t];
				int pick = 0;
				if(arr[i] <= t)
					pick = prev[t - arr[i]];
				cur[t] = (pick + notPick) % mod;
			}
			prev = cur;
		}
		return prev[target];
	}
	
}