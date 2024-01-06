//https://www.codingninjas.com/studio/problems/ninja%E2%80%99s-training_3621003?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    public static int ninjaTraining(int n, int points[][]) {

        return maxMerit(n - 1, 3, points);
    }
    private static int maxMerit(int day, int prev, int[][] points)
    {
        if(day == 0)
        {
            int max = 0;
            for(int i = 0; i < 3; i++)
            {
                if(i != prev)
                {
                    max = Math.max(max, points[0][i]);
                }
            }
            return max;
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 3; i++)
        {
            int curTake = 0;
            if(i != prev)
            {
                curTake = points[day][i] + maxMerit(day - 1, i, points);
            }
            max = Math.max(max, curTake);
        }
        return max;
    
    }

}
//2.memorization
import java.util.Arrays;
import java.util.*;
public class Solution {
    public static int ninjaTraining(int n, int points[][]) {

        int[][] dp = new int[n][4];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return maxMerit(n - 1, 3, points, dp);
    }
    private static int maxMerit(int day, int prev, int[][] points, int[][] dp)
    {
        if(day == 0)
        {
            int max = 0;
            for(int i = 0; i < 3; i++)
            {
                if(i != prev)
                {
                    max = Math.max(max, points[0][i]);
                }
            }
            return dp[day][prev] = max;
        }
        if(dp[day][prev] != -1)
            return dp[day][prev];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 3; i++)
        {
            int curTake = 0;
            if(i != prev)
            {
                curTake = points[day][i] + maxMerit(day - 1, i, points, dp);
            }
            max = Math.max(max, curTake);
        }
        return dp[day][prev] = max;
    
    }

}
//3.tabulation
import java.util.Arrays;
import java.util.*;
public class Solution {
    public static int ninjaTraining(int n, int points[][]) {

        int[][] dp = new int[n][4];
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        for(int day = 1; day < n; day++)
        {
            for(int last = 0; last < 4; last++)
            {
                dp[day][last] = 0;
                for(int task = 0; task < 3; task++)
                {
                    if(last != task)
                    {
                        int curTake = points[day][task] + dp[day - 1][task];
                        dp[day][last] = Math.max(curTake, dp[day][last]);
                    }
                }
            }
        }
        return dp[n - 1][3];
        
    }
}
//4.space optimization
import java.util.Arrays;
import java.util.*;
public class Solution {
    public static int ninjaTraining(int n, int points[][]) {

        int[] prev = new int[4];
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        for(int day = 1; day < n; day++)
        {
            int[] cur = new int[4];
            for(int last = 0; last < 4; last++)
            {
                cur[last] = 0;
                for(int task = 0; task < 3; task++)
                {
                    if(last != task)
                    {
                        int curTake = points[day][task] + prev[task];
                        cur[last] = Math.max(curTake, cur[last]);
                    }
                }
                
            }
            prev = cur;
        }
        return prev[3];
        
    }
}
