//https://www.codingninjas.com/studio/problems/boolean-evaluation_1214650?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
//1.recursion
public class Solution {
    static int MOD = (int)1e9 + 7;
    public static int evaluateExp(String exp) {
        // Write your code here.
        int n = exp.length();
        return (int)totalWays(exp, 0, n - 1, 1);
    }
    static long totalWays(String exp, int i, int j, int isTrue) {
        // Base case 1: When the start index is greater than the end index, no ways to evaluate.
        if (i > j) {
            return 0;
        }
        // Base case 2: When the start and end indices are the same.
        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }

        long ways = 0;
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = totalWays(exp, i, ind - 1, 1);
            long lF = totalWays(exp, i, ind - 1, 0);
            long rT = totalWays(exp, ind + 1, j, 1);
            long rF = totalWays(exp, ind + 1, j, 0);

            char operator = exp.charAt(ind);
            if (operator == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            } else {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                }
            }
        }
        return ways;
    }
}
//2.memorization
public class Solution {
    static int MOD = (int)1e9 + 7;
    public static int evaluateExp(String exp) {
        // Write your code here.
        int n = exp.length();
        Long[][][] dp = new Long[n][n][2];
        return (int)totalWays(exp, 0, n - 1, 1, dp);
    }
    static long totalWays(String exp, int i, int j, int isTrue, Long[][][] dp) {
        // Base case 1: When the start index is greater than the end index, no ways to evaluate.
        if (i > j) {
            return 0;
        }
        // Base case 2: When the start and end indices are the same.
        if (i == j) {
            if (isTrue == 1) {
                return exp.charAt(i) == 'T' ? 1 : 0;
            } else {
                return exp.charAt(i) == 'F' ? 1 : 0;
            }
        }
        if(dp[i][j][isTrue] != null)
            return dp[i][j][isTrue];
        long ways = 0;
        for (int ind = i + 1; ind <= j - 1; ind += 2) {
            long lT = totalWays(exp, i, ind - 1, 1, dp);
            long lF = totalWays(exp, i, ind - 1, 0, dp);
            long rT = totalWays(exp, ind + 1, j, 1, dp);
            long rF = totalWays(exp, ind + 1, j, 0, dp);

            char operator = exp.charAt(ind);
            if (operator == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            } else {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                }
            }
        }
        return dp[i][j][isTrue] = ways;
    }
}