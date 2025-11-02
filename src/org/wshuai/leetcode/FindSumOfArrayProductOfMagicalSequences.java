package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/02/2025.
 * #3539 https://leetcode.com/problems/find-sum-of-array-product-of-magical-sequences/
 */
public class FindSumOfArrayProductOfMagicalSequences {

    private static final int MOD = (int)1e9 + 7;
    private static final int UPPER_BOUND = 31;
    private static final long[] FACTORIAL = new long[UPPER_BOUND]; // f[i] = i!
    private static final long[] INVERSE_FACTORIAL = new long[UPPER_BOUND]; // f[i] = i!^-1

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
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }

    // time O(n * m^3 * k), space O(n * m2 * k)
    public int magicalSumMemorization(int m, int k, int[] nums) {
        int n = nums.length;
        int[][] powVal = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            powVal[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                powVal[i][j] = (int) (1L * powVal[i][j - 1] * nums[i] % MOD);
            }
        }

        int[][][][] memo = new int[n][m + 1][m / 2 + 1][k + 1];
        for (int[][][] a : memo) {
            for (int[][] b : a) {
                for (int[] c : b) {
                    Arrays.fill(c, -1);
                }
            }
        }
        return (int) (dfs(0, m, 0, k, n, powVal, memo) * FACTORIAL[m] % MOD);
    }

    /**
     * DFS with Memorization
     * @param i
     * @param leftM
     * @param x
     * @param leftK
     * @param n
     * @param powVal
     * @param memo
     * @return
     */
    private long dfs(int i, int leftM, int x, int leftK, int n, int[][] powVal, int[][][][] memo) {
        int bitCount = Integer.bitCount(x);
        if (bitCount + leftM < leftK) {
            return 0;
        }
        if (i == n || leftM == 0 || leftK == 0) {
            return leftM == 0 && bitCount == leftK ? 1 : 0;
        }
        if (memo[i][leftM][x][leftK] != -1) {
            return memo[i][leftM][x][leftK];
        }
        long res = 0;
        // For current index i, iterate j from 0 to leftM to select j number of i to the sequence
        for (int j = 0; j <= leftM; j++) {
            // Since we will right shift (x + j) in the next dfs, we need to count in the least set bit
            int bit = (x + j) & 1;
            // Do DFS for the next index i
            // leftM - j: reduce the j number we just picked from the sequence
            // leftK - bit: count in the least set bit
            // (x + j) >> 1: since larger index i will not affect bits on its right, exclude the processed
            //   bits to reduce states
            long r = dfs(i + 1, leftM - j, (x + j) >> 1, leftK - bit, n, powVal, memo);
            // Add the contribution to the final result
            res = (res + r * powVal[i][j] % MOD * INVERSE_FACTORIAL[j]) % MOD;
        }
        return memo[i][leftM][x][leftK] = (int)res;
    }

    // time O(n * m^3 * k), space O(n * m2 * k)
    public int magicalSumDP(int m, int k, int[] nums) {
        int n = nums.length;
        int[][] powVal = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            powVal[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                powVal[i][j] = (int) (1L * powVal[i][j - 1] * nums[i] % MOD);
            }
        }

        int[][][][] dp = new int[n + 1][m + 1][m / 2 + 1][k + 1];
        for (int x = 0; x <= m / 2; x++) {
            int count = Integer.bitCount(x);
            if (count <= k) {
                dp[n][0][x][count] = 1;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int leftM = 0; leftM <= m; leftM++) {
                for (int x = 0; x <= m / 2; x++) {
                    for (int leftK = 0; leftK <= k; leftK++) {
                        long res = 0;
                        for (int j = 0; j <= Math.min(leftM, m - x); j++) {
                            int bit = (x + j) & 1;
                            if (bit <= leftK) {
                                long r = dp[i + 1][leftM - j][(x + j) >> 1][leftK - bit];
                                res = (res + r * powVal[i][j] % MOD * INVERSE_FACTORIAL[j]) % MOD;
                            }
                        }
                        dp[i][leftM][x][leftK] = (int) res;
                    }
                }
            }
        }
        return (int) (1L * dp[0][m][0][k] * FACTORIAL[m] % MOD);
    }
}
