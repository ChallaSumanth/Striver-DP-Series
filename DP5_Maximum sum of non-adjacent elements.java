//https://www.codingninjas.com/studio/problems/maximum-sum-of-non-adjacent-elements_843261?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
//1.recursion
import java.util.* ;
import java.io.*; 
import java.util.*;
public class Solution {
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		// Write your code here.
		int len = nums.size();
		return maximumSum(len - 1, nums);
	}
	private static int maximumSum(int idx, ArrayList<Integer> nums)
	{
		if(idx < 0)
			return 0;
		if(idx == 0)
			return nums.get(idx);
		int pick = nums.get(idx) + maximumSum(idx - 2, nums);
		int notPick = maximumSum(idx - 1, nums);
		return Math.max(pick, notPick);
	}
}
//2.memorization
import java.util.* ;
import java.io.*; 
import java.util.*;
public class Solution {
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		// Write your code here.
		int len = nums.size();
		int[] dp = new int[len];
		Arrays.fill(dp, -1);
		return maximumSum(len - 1, nums, dp);
	}
	private static int maximumSum(int idx, ArrayList<Integer> nums, int[] dp)
	{
		if(idx < 0)
			return 0;
		if(idx == 0)
			return nums.get(idx);
		if(dp[idx] != -1)
			return dp[idx];
		int pick = nums.get(idx) + maximumSum(idx - 2, nums, dp);
		int notPick = maximumSum(idx - 1, nums, dp);
		return dp[idx] = Math.max(pick, notPick);
	}
}
//3.tabulation
import java.util.* ;
import java.io.*; 
import java.util.*;
public class Solution {
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		// Write your code here.
		int len = nums.size();
		int[] dp = new int[len];
		dp[0] = nums.get(0);
		if(len == 1)
			return dp[0];
		for(int i = 1; i < len; i++)
		{
			int take = nums.get(i);
			if(i >= 2)
				take += dp[i - 2];
			int notTake = dp[i - 1];
			dp[i] = Math.max(take, notTake);
		}
		return dp[len - 1];
	}
}
//4.space optimization
import java.util.* ;
import java.io.*; 
import java.util.*;
public class Solution {
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		// Write your code here.
		int len = nums.size();
		int[] dp = new int[len];
		int prev = nums.get(0);
		if(len == 1)
			return prev;
		int prev1 = 0;
		for(int i = 1; i < len; i++)
		{
			int take = nums.get(i) + prev1;
			int notTake = prev;
			int cur = Math.max(take, notTake);
			prev1 = prev;
			prev = cur;
		}
		return prev;
	}
}