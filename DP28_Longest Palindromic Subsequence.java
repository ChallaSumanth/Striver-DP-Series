//https://takeuforward.org/data-structure/longest-palindromic-subsequence-dp-28/
//1.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int longestPalindromeSubsequence(String s) {
		// Write your code here.
		StringBuilder sb = new StringBuilder(s);
		String s1 = sb.reverse().toString();
		int len = s.length();
		int[][] dp = new int[len + 1][len + 1];
		for(int i = 1; i <= len; i++)
		{
			for(int j = 1; j <= len; j++)
			{
				if(s.charAt(i - 1) == s1.charAt(j - 1))
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[len][len];
	}
}
//2.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
	public static int longestPalindromeSubsequence(String s) {
		// Write your code here.
		StringBuilder sb = new StringBuilder(s);
		String s1 = sb.reverse().toString();
		int len = s.length();
		int[] prev = new int[len + 1];
		for(int i = 1; i <= len; i++)
		{
			int[] cur = new int[len + 1];
			for(int j = 1; j <= len; j++)
			{
				if(s.charAt(i - 1) == s1.charAt(j - 1))
					cur[j] = 1 + prev[j - 1];
				else
					cur[j] = Math.max(prev[j], cur[j - 1]);
			}
			prev = cur;
		}
		return prev[len];
	}
}