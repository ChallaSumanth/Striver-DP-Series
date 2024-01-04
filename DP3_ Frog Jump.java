//https://www.codingninjas.com/studio/problems/frog-jump_3621012?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int frogJump(int n, int heights[]) {

        // Write your code here..
        return minEnergy(n - 1, heights);
    }
    private static int minEnergy(int idx, int[] heights)
    {
        if(idx <= 0)
            return 0;
        int firstStep = Integer.MAX_VALUE;
        int secondStep = Integer.MAX_VALUE;

        if(idx > 0)
            firstStep = Math.abs(heights[idx] - heights[idx - 1])
             + minEnergy(idx - 1, heights);
        if(idx > 1)
            secondStep = Math.abs(heights[idx] - heights[idx - 2])
            +minEnergy(idx - 2, heights);
        return Math.min(firstStep, secondStep);
    }

}
//2.Memorization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int frogJump(int n, int heights[]) {

        // Write your code here..
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return minEnergy(n - 1, heights, dp);
    }
    private static int minEnergy(int idx, int[] heights, int[] dp)
    {
        if(idx <= 0)
            return 0;
        if(dp[idx] != -1)
            return dp[idx];
        int firstStep = Integer.MAX_VALUE;
        int secondStep = Integer.MAX_VALUE;

        if(idx > 0)
            firstStep = Math.abs(heights[idx] - heights[idx - 1])
             + minEnergy(idx - 1, heights, dp);
        if(idx > 1)
            secondStep = Math.abs(heights[idx] - heights[idx - 2])
            +minEnergy(idx - 2, heights, dp);
        return dp[idx] = Math.min(firstStep, secondStep);
    }

}
//3.Tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int frogJump(int n, int heights[]) {

        // Write your code here..
        int[] dp = new int[n];
        for(int i = 1; i < n; i++)
        {
            int firstStep = Integer.MAX_VALUE;
            int secondStep = Integer.MAX_VALUE;
            if(i > 0)
               firstStep = Math.abs(heights[i] - heights[i - 1]) + dp[i - 1];
            if(i > 1)
               secondStep = Math.abs(heights[i] - heights[i - 2]) + dp[i - 2];
            dp[i] = Math.min(firstStep, secondStep);
        }
        return dp[n - 1];
    }

}
//4.space optimization
import java.util.* ;
import java.io.*; 
public class Solution {
    public static int frogJump(int n, int heights[]) {

        // Write your code here..
        int prev2 = 0;
        int prev1 = 0;
        for(int i = 1; i < n; i++)
        {
            
            int firstStep = Math.abs(heights[i] - heights[i - 1]) + prev1;
            int secondStep = Integer.MAX_VALUE;
            if(i > 1)
               secondStep = Math.abs(heights[i] - heights[i - 2]) + prev2;
            int cur = Math.min(firstStep, secondStep);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

}