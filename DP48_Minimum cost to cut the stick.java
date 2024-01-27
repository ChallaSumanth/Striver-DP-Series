//https://www.codingninjas.com/studio/problems/cost-to-cut-a-chocolate_3208460?leftPanelTabValue=SUBMISSION
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int cost(int n, int c, int cuts[]) {
        List<Integer> Cuts = new ArrayList<>();
        Cuts.add(0);
        Cuts.add(n);
        for(int cut : cuts)
            Cuts.add(cut);
        Collections.sort(Cuts);
        return minTotalCost(1, c, Cuts);
    }
    private static int minTotalCost(int i, int j, List<Integer> cuts)
    {
        if(i > j)
            return 0;
        int minCost = (int)1e9;
        for(int ind = i; ind <= j; ind++)
        {
            int cost = cuts.get(j + 1) - cuts.get(i - 1) +
                        minTotalCost(i, ind - 1, cuts) +
                        minTotalCost(ind + 1, j, cuts);
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }
}
//2.memorization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int cost(int n, int c, int cuts[]) {
        List<Integer> Cuts = new ArrayList<>();
        Cuts.add(0);
        Cuts.add(n);
        for(int cut : cuts)
            Cuts.add(cut);
        Collections.sort(Cuts);
        int[][] dp = new int[n + 1][c + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return minTotalCost(1, c, Cuts, dp);
    }
    private static int minTotalCost(int i, int j, List<Integer> cuts, int[][] dp)
    {
        if(i > j)
            return 0;
        int minCost = (int)1e9;
        if(dp[i][j] != -1)
            return dp[i][j];
        for(int ind = i; ind <= j; ind++)
        {
            int cost = cuts.get(j + 1) - cuts.get(i - 1) +
                        minTotalCost(i, ind - 1, cuts, dp) +
                        minTotalCost(ind + 1, j, cuts, dp);
            minCost = Math.min(minCost, cost);
        }
        return dp[i][j] = minCost;
    }
}
//3.tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int cost(int n, int c, int cuts[]) {
        List<Integer> Cuts = new ArrayList<>();
        Cuts.add(0);
        Cuts.add(n);
        for(int cut : cuts)
            Cuts.add(cut);
        Collections.sort(Cuts);
        int[][] dp = new int[c + 2][c + 2];
        for(int i = c; i >= 1; i--)
        {
            for(int j = 1; j <= c; j++)
            {
                if(i > j)
                    continue;
                int minCost = (int)1e9;
                for(int ind = i; ind <= j; ind++)
                {
                    int cost = Cuts.get(j + 1) - Cuts.get(i - 1) +
                                dp[i][ind - 1] + dp[ind + 1][j];
                    minCost = Math.min(minCost, cost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][c];
    }
}