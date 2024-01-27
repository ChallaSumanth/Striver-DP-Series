//https://www.codingninjas.com/studio/problems/longest-bitonic-sequence_1062688?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
public class Solution {
    public static int longestBitonicSequence(int[] arr, int n) {
        // Write your code here.
        int[] dp1 = new int[n];
        for(int i = 0; i < n; i++)
        {
            dp1[i] = 1;
            for(int prev = 0; prev < i; prev++)
            {
                if(arr[i] > arr[prev] && 1 + dp1[prev] > dp1[i])
                    dp1[i] = 1 + dp1[prev];
            }
        }
        int max = 1;
        int[] dp2 = new int[n];
        for(int i = n - 1; i >= 0; i--)
        {
            dp2[i] = 1;
            for(int prev = n - 1; prev > i; prev--)
            {
                if(arr[i] > arr[prev] && 1 + dp2[prev] > dp2[i])
                    dp2[i] = 1 + dp2[prev];
            }
            max = Math.max(dp1[i] + dp2[i] - 1, max);
        }
        return max;
    }
}
