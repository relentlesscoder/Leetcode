package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/02/2025.
 * #3343 https://leetcode.com/problems/count-number-of-balanced-permutations/
 */
public class CountNumberOfBalancedPermutations {

    private static final int MOD = (int)1e9 + 7;
    private static final int UPPER_BOUND = 41;
    private static final long[] FACTORIAL = new long[UPPER_BOUND];
    private static final long[] INVERSE_FACTORIAL = new long[UPPER_BOUND];

    static {
        FACTORIAL[0] = 1;
        for (int i = 1; i < UPPER_BOUND; i++) {
            FACTORIAL[i] = (FACTORIAL[i - 1] * i) % MOD;
        }
        INVERSE_FACTORIAL[UPPER_BOUND - 1] = pow(FACTORIAL[UPPER_BOUND - 1], MOD - 2);
        for (int i = UPPER_BOUND - 1; i > 0; i--) {
            INVERSE_FACTORIAL[i - 1] = (INVERSE_FACTORIAL[i] * i) % MOD;
        }
    }

    private static long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }

    // time O(n^2 * S), space O(n * S)
    public int countBalancedPermutations(String num) {
        int[] freq = new int[10];
        int n = num.length(), m = n / 2, total = 0;
        for (char c : num.toCharArray()) {
            freq[c - '0']++;
            total += c - '0';
        }
        // Total can't be divided by 2 evenly
        if (total % 2 == 1) {
            return 0;
        }
        // Prefix sum
        for (int i = 1; i < 10; i++) {
            freq[i] += freq[i - 1];
        }
        int[][][] memo = new int[10][m + 1][total / 2 + 1];
        for (int[][] matrix : memo) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }
        return (int) (FACTORIAL[m] * FACTORIAL[n - m] % MOD * dfs(9, m, total / 2, freq, memo) % MOD);
    }

    /**
     * DFS with Memorization
     * @param i current digit [0 - 9]
     * @param count count of digits needed
     * @param sum sum of digits needed
     * @param freq digit frequency map
     * @param memo memorization map
     * @return total number of balanced permutations
     */
    private int dfs(int i, int count, int sum, int[] freq, int[][][] memo) {
        if (i < 0) {
            return sum == 0 ? 1 : 0;
        }
        if (memo[i][count][sum] != -1) {
            return memo[i][count][sum];
        }
        long res = 0;
        int c = freq[i] - (i > 0 ? freq[i - 1] : 0); // frequency of digit i
        int leftCount = count - (i > 0 ? freq[i - 1] : 0); // leftCount is the minimum i that needs to be selected
        // Continue DFS by selecting count k of digit i
        for (int k = Math.max(leftCount, 0); k <= Math.min(c, count) && k * i <= sum; k++) {
            long r = dfs(i - 1, count - k, sum - k * i, freq, memo);
            // Deduplicate: res / k! * (c - k)!
            res = (res + r * INVERSE_FACTORIAL[k] % MOD * INVERSE_FACTORIAL[c - k]) % MOD;
        }
        return memo[i][count][sum] = (int) res;
    }

    // time O(n^2 * S), space O(n * S)
    public int countBalancedPermutationsDP(String num) {
        int[] freq = new int[10];
        int n = num.length(), m = n / 2, total = 0;
        for (char c : num.toCharArray()) {
            freq[c - '0']++;
            total += c - '0';
        }
        // Total can't be divided by 2 evenly
        if (total % 2 == 1) {
            return 0;
        }
        int[][] dp = new int[m + 1][total / 2 + 1];
        dp[0][0] = 1;
        int sc = 0;
        int s = 0;
        for (int i = 0; i < 10; i++) {
            int c = freq[i];
            sc += c;
            s += c * i;
            // Ensure left2 <= n - n1, left1 >= sc - (n - n1)
            for (int left1 = Math.min(sc, m); left1 >= Math.max(sc - (n - m), 0); left1--) {
                int left2 = sc - left1;
                // Ensure leftS >= s - total/2
                for (int leftS = Math.min(s, total / 2); leftS >= Math.max(s - total / 2, 0); leftS--) {
                    long res = 0;
                    for (int k = Math.max(c - left2, 0); k <= Math.min(c, left1) && k * i <= leftS; k++) {
                        res = (res + dp[left1 - k][leftS - k * i] * INVERSE_FACTORIAL[k] % MOD * INVERSE_FACTORIAL[c - k]) % MOD;
                    }
                    dp[left1][leftS] = (int) res;
                }
            }
        }
        return (int) (FACTORIAL[m] * FACTORIAL[n - m] % MOD * dp[m][total / 2] % MOD);
    }

    /*
    public int countBalancedPermutations(String num) {
        int[] freq = new int[10];
        int n = num.length(), m = n / 2, total = 0;
        for (char c : num.toCharArray()) {
            freq[c - '0']++;
            total += c - '0';
        }
        if (total % 2 == 1) {
            return 0;
        }
        int[][][] memo = new int[n + 1][m + 1][total / 2 + 1];
        for (int[][] matrix : memo) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }
        long res = (FACTORIAL[m] * FACTORIAL[n - m] % MOD * dfs(n - 1, num, m, total / 2, memo) % MOD);
        for (int i = 0; i < 10; i++) {
            res = res * INVERSE_FACTORIAL[freq[i]] % MOD;
        }
        return (int) res;
    }

    private int dfs(int i, String num, int count, int sum, int[][][] memo) {
        if (i < 0) {
            return count == 0 && sum == 0 ? 1 : 0;
        }
        if (memo[i][count][sum] != -1) {
            return memo[i][count][sum];
        }
        long res = dfs(i - 1, num, count, sum, memo) % MOD;
        int d = num.charAt(i) - '0';
        if (sum >= d && count > 0) {
            res = (res + dfs(i - 1, num, count - 1, sum - d, memo)) % MOD;
        }
        return memo[i][count][sum] = (int) res;
    }
     */
}
