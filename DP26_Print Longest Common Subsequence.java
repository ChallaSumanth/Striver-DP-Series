//https://www.codingninjas.com/studio/problems/print-longest-common-subsequence_8416383?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=SUBMISSION
//DP 25 is prereq
public class Solution {
    public static String findLCS(int n, int m, String s, String t){
        // Write your code here.
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
		int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j > 0)
        {
            if(s.charAt(i) == t.charAt(j))
            {
                sb.append(s.charAt(i));
                i--;
                j--;
            }
            else if(dp[i - 1][j] > dp[i][j - 1])
            {
                i--;
            }
            else
                j--;
        }
        //System.out.println(sb.toString());
        return sb.reverse().toString();
    }
}