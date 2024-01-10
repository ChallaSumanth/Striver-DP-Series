//https://www.codingninjas.com/studio/problems/partition-equal-subset-sum-_892980?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion 
import java.util.Arrays;

public class Solution {
	public static boolean canPartition(int[] arr, int n) {
		// Write your code here.
		int sum = 0;
		for(int val : arr)
			sum += val;
		if(sum % 2 == 1)
			return false;
		int curSum = 0;
		return canForm(n - 1, arr, 0, sum / 2);
	}
	private static boolean canForm(int idx, int[] arr, int curSum, int sum)
	{
		if(idx < 0)
			return false;
		if(sum == curSum)
			return true;
		if(idx == 0)
			return arr[0] == sum;
		boolean notTake = canForm(idx - 1, arr, curSum, sum);
		boolean take = false;
		if(curSum + arr[idx] <= sum)
			take = canForm(idx - 1, arr, curSum + arr[idx], sum);
		return take || notTake;
	}
}
//2.memorization
import java.util.Arrays;

public class Solution {
	public static boolean canPartition(int[] arr, int n) {
		// Write your code here.
		int sum = 0;
		for(int val : arr)
			sum += val;
		if(sum % 2 == 1)
			return false;
		int curSum = 0;
		int[][] dp = new int[n][(sum / 2) + 1];
		for(int[] row : dp)
			Arrays.fill(row, -1);
		return canForm(n - 1, arr, 0, sum / 2, dp);
	}
	private static boolean canForm(int idx, int[] arr, int curSum, int sum, int[][] dp)
	{
		if(idx < 0)
			return false;
		if(sum == curSum)
			return true;
		if(dp[idx][curSum] != -1)
			return dp[idx][curSum] == 0 ? false : true;
		if(idx == 0)
			return arr[0] == sum;
		boolean notTake = canForm(idx - 1, arr, curSum, sum, dp);
		boolean take = false;
		if(curSum + arr[idx] <= sum)
			take = canForm(idx - 1, arr, curSum + arr[idx], sum, dp);
		dp[idx][curSum] = (take || notTake) ? 1 : 0;
		return take || notTake;
		
	}
}
//3.tabulation
import java.util.Arrays;

public class Solution {
	public static boolean canPartition(int[] arr, int n) {
		// Write your code here.
		int sum = 0;
		for(int val : arr)
			sum += val;
		if(sum % 2 == 1)
			return false;
		int curSum = 0;
		int k = sum / 2;
		boolean[][] dp = new boolean[n][k + 1];
		for(int i = 0; i < n; i++)
			dp[i][0] = true;
		dp[0][arr[0]] = true;

		for(int i = 1; i < n; i++)
		{
			for(int target = 1; target <= k; target++)
			{
				boolean notTake = dp[i - 1][target];

				boolean take = false;
				if(arr[i] <= target)
					take = dp[i - 1][target - arr[i]];
				dp[i][target] = take || notTake;
			}
		}
		return dp[n - 1][k];
	}
}
//4.space optimization
import java.util.Arrays;

public class Solution {
	public static boolean canPartition(int[] arr, int n) {
		// Write your code here.
		int sum = 0;
		for(int val : arr)
			sum += val;
		if(sum % 2 == 1)
			return false;
		int curSum = 0;
		int k = sum / 2;
		boolean[] prev = new boolean[k + 1];
		prev[0] = true;
		prev[arr[0]] = true;

		for(int i = 1; i < n; i++)
		{
			boolean[] cur = new boolean[k + 1];
			for(int target = 1; target <= k; target++)
			{
				boolean notTake = prev[target];

				boolean take = false;
				if(arr[i] <= target)
					take = prev[target - arr[i]];
				cur[target] = take || notTake;
			}
			prev = cur;
		}
		return prev[k];
	}
}