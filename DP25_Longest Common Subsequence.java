//https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/
//1.recursion

public class Solution {

	public static int lcs(String s, String t) {
		//Your code goes here
		int m = s.length();
		int n = t.length();
		return maxLen(m - 1, s, n - 1, t);
    }

	private static int maxLen(int m, String s, int n, String t)
	{
		if(m < 0 || n < 0)
			return 0;
		if(s.charAt(m) == t.charAt(n))
			return 1 + maxLen(m - 1, s, n - 1, t);
		else
			return Math.max(maxLen(m - 1, s, n, t) , maxLen(m, s, n - 1, t));
		
	}

}
//2.memorization
import java.util.*;
public class Solution {

	public static int lcs(String s, String t) {
		//Your code goes here
		int m = s.length();
		int n = t.length();
		int[][] dp = new int[m][n];
		for(int[] row : dp)
			Arrays.fill(row, -1);
		return maxLen(m - 1, s, n - 1, t, dp);
    }

	private static int maxLen(int m, String s, int n, String t, int[][] dp)
	{
		if(m < 0 || n < 0)
			return 0;
		if(dp[m][n] != -1)
			return dp[m][n];
		if(s.charAt(m) == t.charAt(n))
			return dp[m][n] = 1 + maxLen(m - 1, s, n - 1, t, dp);
		else
			return dp[m][n] = Math.max(maxLen(m - 1, s, n, t, dp) , maxLen(m, s, n - 1, t, dp));
		
	}

}
//3.tabulation
import java.util.*;
public class Solution {

	public static int lcs(String s, String t) {
		//Your code goes here
		int m = s.length();
		int n = t.length();
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 1; i <= m; i++)
		{
			for(int j = 1; j <= n; j++)
			{
				if(s.charAt(i - 1) == t.charAt(j - 1))
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[m][n];
    }
}
//4.space optimization
import java.util.*;
public class Solution {

	public static int lcs(String s, String t) {
		//Your code goes here
		int m = s.length();
		int n = t.length();
		int[]prev = new int[n + 1];
		for(int i = 1; i <= m; i++)
		{
			int[] cur = new int[n + 1];
			for(int j = 1; j <= n; j++)
			{
				if(s.charAt(i - 1) == t.charAt(j - 1))
					cur[j] = 1 + prev[j - 1];
				else
					cur[j] = Math.max(prev[j], cur[j - 1]);
			}
			prev = cur;
		}
		return prev[n];
    }
}