package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/01/2025.
 * #3428 https://leetcode.com/problems/maximum-and-minimum-sums-of-at-most-size-k-subsequences/
 */
public class MaximumAndMinimumSumsOfAtMostSizeKSubsequences {

    private static final int MOD = (int)1e9 + 7;
    private static final int UPPER_BOUND = 100_000;

    private static final long[] FACTORIAL = new long[UPPER_BOUND]; // f[i] = i!
    private static final long[] INVERSE_FACTORIAL = new long[UPPER_BOUND]; // f[i] = i!^-1

    static {
        FACTORIAL[0] = 1;
        for (int i = 1; i < UPPER_BOUND; i++) {
            FACTORIAL[i] = FACTORIAL[i - 1] * i % MOD;
        }
        INVERSE_FACTORIAL[UPPER_BOUND - 1] = pow(FACTORIAL[UPPER_BOUND - 1], MOD - 2);
        for (int i = UPPER_BOUND - 1; i > 0; i--) {
            INVERSE_FACTORIAL[i - 1] = INVERSE_FACTORIAL[i] * i % MOD;
        }
    }

    // time O(n * log(n)), space O(1)
    public int minMaxSums(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            // for subsequence using nums[i] as maximum, we can select from 0 to Math.min(k - 1, i)
            // numbers from [0, i - 1]
            for (int j = 0; j < Math.min(k, i + 1); j++) {
                sum += combination(i, j);
            }
            // select nums[i] as the maximum and select nums[n - 1 - i] as the minimum
            // are symmetric
            res = (res + sum % MOD * (nums[i] + nums[n - 1 - i])) % MOD;
        }
        return (int) res;
    }

    private long combination(int n, int m) {
        return FACTORIAL[n] * INVERSE_FACTORIAL[m] % MOD * INVERSE_FACTORIAL[n - m] % MOD;
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
}
