//https://www.codingninjas.com/studio/problems/longest-increasing-subsequence_630459?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion

public class Solution {

	public static int longestIncreasingSubsequence(int arr[]) {
		//Your code goes here
		int len = arr.length;
		return LIS(0, len, arr, -1);
	}
	private static int LIS(int idx, int n, int[] arr, int prev)
	{
		if(idx == n)
			return 0;
		int notTake = LIS(idx + 1, n, arr, prev);
		int take = 0;
		if(prev == -1 || arr[idx] > arr[prev])
			take = 1 + LIS(idx + 1, n, arr, idx);
		return Math.max(take, notTake);
	}

}
//2.memorization
import java.util.Arrays;

public class Solution {

	public static int longestIncreasingSubsequence(int arr[]) {
		//Your code goes here
		int len = arr.length;
		int[][] dp = new int[len][len + 1];
		for(int[] row : dp)
			Arrays.fill(row, -1);
		return LIS(0, len, arr, -1, dp);
	}
	private static int LIS(int idx, int n, int[] arr, int prev, int[][] dp)
	{
		if(idx == n)
			return 0;
		if(dp[idx][prev + 1] != -1)
			return dp[idx][prev + 1];
		int notTake = LIS(idx + 1, n, arr, prev, dp);
		int take = 0;
		if(prev == -1 || arr[idx] > arr[prev])
			take = 1 + LIS(idx + 1, n, arr, idx, dp);
		return dp[idx][prev + 1] = Math.max(take, notTake);
	}

}
//3.tabulation
public class Solution {

	public static int longestIncreasingSubsequence(int arr[]) {
		//Your code goes here
		int n = arr.length;
		int[][] dp = new int[n + 1][n + 1];
		for(int i = n - 1; i >= 0; i--)
		{
			for(int prev = i - 1; prev >= -1; prev--)
			{
				int notTake = dp[i + 1][prev + 1];
				int take = 0;
				if(prev == -1 || arr[i] > arr[prev])
					take = 1 + dp[i + 1][i + 1];
				dp[i][prev + 1] = Math.max(take, notTake);
			}
		}
		return dp[0][0];
    }
}
//4.space optimization
import java.util.Arrays;

public class Solution {

	public static int longestIncreasingSubsequence(int arr[]) {
		//Your code goes here
		int n = arr.length;
		int[] next = new int[n + 1];
		for(int i = n - 1; i >= 0; i--)
		{
			int[] cur = new int[n + 1];
			for(int prev = i - 1; prev >= -1; prev--)
			{
				int notTake = next[prev + 1];
				int take = 0;
				if(prev == -1 || arr[i] > arr[prev])
					take = 1 + next[i + 1];
				cur[prev + 1] = Math.max(take, notTake);
			}
			next = cur;
		}
		return next[0];
	}

}
//using 1-D Array
import java.util.Arrays;

public class Solution {

	public static int longestIncreasingSubsequence(int arr[]) {
		//Your code goes here
		int n = arr.length;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		int max = 1;
		for(int i = 0; i < n; i++)
		{
			for(int prev = 0; prev < i; prev++)
			{
				if(arr[prev] < arr[i])
					dp[i] = Math.max(1 + dp[prev], dp[i]);
			}
			max = Math.max(max, dp[i]);
		}
		return max;
		
	}

}
//optimal

