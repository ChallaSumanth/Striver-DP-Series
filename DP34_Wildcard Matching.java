//https://www.codingninjas.com/studio/problems/wildcard-pattern-matching_701650?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
	public static boolean wildcardMatching(String pattern, String text) {
		// Write your code here.
		int m = pattern.length();
		int n = text.length();
		return isMatching(pattern, m - 1, text, n - 1);
	}

	private static boolean isMatching(String m, int i, String n, int j)
	{
		if(i < 0 && j < 0)
			return true;
		if(i < 0 && j >= 0)
			return false;
		if(i >= 0 && j < 0)
			return isAllStars(m, i);
		if(m.charAt(i) == n.charAt(j) || m.charAt(i) == '?')
			return isMatching(m, i - 1, n, j - 1);
		else
		{
			if(m.charAt(i) == '*')
				return isMatching(m, i - 1, n, j) || isMatching(m, i, n, j - 1);
			else
				return false;
		}
	}

	private static boolean isAllStars(String m, int idx)
	{
		for(int i = 0; i <= idx; i++)
		{
			if(m.charAt(i) != '*')
				return false;
		}
		return true;
	}
}
//2.memorization
import java.util.Arrays;

public class Solution {
	public static boolean wildcardMatching(String pattern, String text) {
		// Write your code here.
		int m = pattern.length();
		int n = text.length();
		Boolean[][] dp = new Boolean[m][n];
		return isMatching(pattern, m - 1, text, n - 1, dp);
	}
	private static boolean isMatching(String m, int i, String n, int j, Boolean[][] dp)
	{
		if(i < 0 && j < 0)
			return true;
		if(i < 0 && j >= 0)
			return false;
		if(i >= 0 && j < 0)
			return isAllStars(m, i);
		if(dp[i][j] != null)
			return dp[i][j];
		if(m.charAt(i) == n.charAt(j) || m.charAt(i) == '?')
			return dp[i][j] = isMatching(m, i - 1, n, j - 1, dp);
		else
		{
			if(m.charAt(i) == '*')
				return dp[i][j] = isMatching(m, i - 1, n, j, dp) || isMatching(m, i, n, j - 1, dp);
			else
				return dp[i][j] = false;
		}
	}

	private static boolean isAllStars(String m, int idx)
	{
		for(int i = 0; i <= idx; i++)
		{
			if(m.charAt(i) != '*')
				return false;
		}
		return true;
	}
}
//3.Tabulation
import java.util.Arrays;

public class Solution {
	public static boolean wildcardMatching(String pattern, String text) {
		// Write your code here.
		int m = pattern.length();
		int n = text.length();
		Boolean[][] dp = new Boolean[m + 1][n + 1];
		dp[0][0] = true;
		for(int j = 1; j <= n; j++)
			dp[0][j] = false;
		for(int i = 1; i <= m; i++)
			dp[i][0] = isAllStars(pattern, i);
		for(int i = 1; i <= m; i++)
		{
			for(int j = 1; j <= n; j++)
			{
				if(pattern.charAt(i - 1) == text.charAt(j - 1) || pattern.charAt(i - 1) == '?')
					dp[i][j] = dp[i - 1][j - 1];
				else
				{
					if(pattern.charAt(i - 1) == '*')
						dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
					else
						dp[i][j] = false;
				}
			}
		}
		return dp[m][n];
	}
	
	private static boolean isAllStars(String m, int idx)
	{
		for(int i = 1; i <= idx; i++)
		{
			if(m.charAt(i - 1) != '*')
				return false;
		}
		return true;
	}
}
//4.space optimization
import java.util.Arrays;

public class Solution {
	public static boolean wildcardMatching(String pattern, String text) {
		// Write your code here.
		int m = pattern.length();
		int n = text.length();
		Boolean[] prev = new Boolean[n + 1];
		prev[0] = true;
		for(int j = 1; j <= n; j++)
			prev[j] = false;
		//for(int i = 1; i <= m; i++)
		//	dp[i][0] = isAllStars(pattern, i);
		for(int i = 1; i <= m; i++)
		{
			Boolean[] cur = new Boolean[n + 1];
			cur[0] = isAllStars(pattern, i);
			for(int j = 1; j <= n; j++)
			{
				if(pattern.charAt(i - 1) == text.charAt(j - 1) || pattern.charAt(i - 1) == '?')
					cur[j] = prev[j - 1];
				else
				{
					if(pattern.charAt(i - 1) == '*')
						cur[j] = prev[j] || cur[j - 1];
					else
						cur[j] = false;
				}
			}
			prev = cur;
		}
		return prev[n];
	}
	
	private static boolean isAllStars(String m, int idx)
	{
		for(int i = 1; i <= idx; i++)
		{
			if(m.charAt(i - 1) != '*')
				return false;
		}
		return true;
	}
}