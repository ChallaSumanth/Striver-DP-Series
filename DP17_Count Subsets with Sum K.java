//https://takeuforward.org/data-structure/count-subsets-with-sum-k-dp-17/
//1.recursion
import java.util.*;
import java.io.*;

public class Solution {
    public static int findWays(int num[], int tar) {
        // Write your code here.
        int n = num.length;
        return countWays(n - 1, num, tar);
    }

    private static int countWays(int idx, int[] num, int tar)
    {
        if(tar == 0)
            return 1;
        if(idx == 0)
        {
            if(tar == num[idx])
            {
                return 1;
            }
            else
                return 0;
        }
        int notPick = countWays(idx - 1, num, tar);
        int pick = 0;
        if(num[idx] <= tar)
            pick += countWays(idx - 1, num, tar - num[idx]);

        return pick + notPick;
    }
}
//2.memorization
import java.util.*;
import java.io.*;

public class Solution {
    public static int findWays(int num[], int tar) {
        // Write your code here.
        int n = num.length;
        int[][] dp = new int[n][tar + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return countWays(n - 1, num, tar, dp);
    }

    private static int countWays(int idx, int[] num, int tar, int[][] dp)
    {
        if(tar == 0)
            return 1;
        if(idx == 0)
        {
            if(tar == num[idx])
            {
                return 1;
            }
            else
                return 0;
        }
        if(dp[idx][tar] != -1)
            return dp[idx][tar];
        int notPick = countWays(idx - 1, num, tar, dp);
        int pick = 0;
        if(num[idx] <= tar)
            pick += countWays(idx - 1, num, tar - num[idx], dp);

        return dp[idx][tar] = pick + notPick;
    }
}
//3.tabulation
import java.util.*;
import java.io.*;

public class Solution {
    public static int findWays(int num[], int tar) {
       // Write your code here.
        int n = num.length;
        int[][] dp = new int[n][tar + 1];
        for(int i = 0; i < n; i++)
            dp[i][0] = 1;
        if(num[0] <= tar)
            dp[0][num[0]] = 1;
        int mod = (int)1e9 + 7;
        Arrays.sort(num);
        for(int i = 1; i < n; i++)
        {
            for(int t = 1; t <= tar; t++)
            {
                int notPick = dp[i - 1][t] % mod;
                int pick = 0;
                if(num[i] <= t)
                    pick = (pick % mod + dp[i - 1][t - num[i]] % mod);
                dp[i][t] = (pick % mod + notPick % mod) % mod;
            }
        }
        return dp[n - 1][tar];
    
    }
}
//4.space optimization
import java.util.*;
import java.io.*;

public class Solution {
    public static int findWays(int num[], int tar) {
       // Write your code here.
        int n = num.length;
        int[] prev = new int[tar + 1];
        prev[0] = 1;
        if(num[0] <= tar)
            prev[num[0]] = 1;
        int mod = (int)1e9 + 7;
        
        for(int i = 1; i < n; i++)
        {
            int[] cur = new int[tar + 1];
            cur[0] = 1;
            for(int t = 1; t <= tar; t++)
            {
                int notPick = prev[t] % mod;
                int pick = 0;
                if(num[i] <= t)
                    pick = (pick % mod + prev[t - num[i]] % mod);
                cur[t] = (pick % mod + notPick % mod) % mod;
            }
            prev = cur;
        }
        return prev[tar];
    
    }
}