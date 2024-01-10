//https://www.codingninjas.com/studio/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum._842494?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    public static int minSubsetSumDifference(int []arr, int n) {
        // Write your code here.
        int sum = 0;
        for(int num : arr)
            sum += num;
        return minDiff(n - 1, arr, 0, sum);
    }
    private static int minDiff(int idx, int[] arr, int curSum, int sum)
    {
        if(idx == 0)
            return Math.abs(curSum - sum);
        int notTake = minDiff(idx - 1, arr, curSum, sum);
        int take = minDiff(idx - 1, arr, curSum + arr[idx], sum - arr[idx]);

        return Math.min(take, notTake);

    }
}
//2.memorization
import java.util.Arrays;

public class Solution {
    public static int minSubsetSumDifference(int []arr, int n) {
        // Write your code here.
        int sum = 0;
        for(int num : arr)
            sum += num;
        int[][] dp = new int[n][sum + 1];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return minDiff(n - 1, arr, 0, sum, dp);
    }
    private static int minDiff(int idx, int[] arr, int curSum, int sum, int[][] dp)
    {
        if(idx == 0)
            return Math.abs(curSum - sum);
        if(dp[idx][curSum] != -1)
            return dp[idx][curSum];
        int notTake = minDiff(idx - 1, arr, curSum, sum, dp);
        int take = minDiff(idx - 1, arr, curSum + arr[idx], sum - arr[idx], dp);

        return dp[idx][curSum] = Math.min(take, notTake);

    }
}
//3.tabulation
import java.util.Arrays;

public class Solution {
    public static int minSubsetSumDifference(int []arr, int n) {
        // Write your code here.
        int sum = 0;
        for(int num : arr)
            sum += num;
        boolean[][] dp = new boolean[n][sum + 1];
       for(int i = 0; i < n; i++)
            dp[i][0] = true;
        if(arr[0] <= sum)
            dp[0][arr[0]] = true;
        for(int i = 1; i < n; i++)
        {
            for(int target = 0; target <= sum; target++)
            {
                boolean notTake = dp[i - 1][target];
                boolean take = false;
                if(target >= arr[i])
                    take = dp[i - 1][target - arr[i]];
                dp[i][target] = notTake || take;
            }
        }
        int minDiff = Integer.MAX_VALUE;

        for(int i = 0; i <= sum; i++)
        {
            if(dp[n - 1][i])
                minDiff = Math.min(minDiff, Math.abs(i - (sum - i)));
        }
        return minDiff;
    }
    
}
//4.space optimization
import java.util.Arrays;

public class Solution {
    public static int minSubsetSumDifference(int []arr, int n) {
        // Write your code here.
        int sum = 0;
        for(int num : arr)
            sum += num;
        boolean[] prev = new boolean[sum + 1];
        prev[0] = true;
        if(arr[0] <= sum)
            prev[arr[0]] = true;
        for(int i = 1; i < n; i++)
        {
            boolean[] cur = new boolean[sum + 1];
            for(int target = 1; target <= sum; target++)
            {
                boolean notTake = prev[target];
                boolean take = false;
                if(target >= arr[i])
                    take = prev[target - arr[i]];
                cur[target] = notTake || take;
            }
            prev = cur;
        }
        int minDiff = Integer.MAX_VALUE;

        for(int i = 0; i <= sum; i++)
        {
            if(prev[i])
                minDiff = Math.min(minDiff, Math.abs(i - (sum - i)));
        }
        return minDiff;
    }
    
}