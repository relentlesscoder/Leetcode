package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3405 https://leetcode.com/problems/count-the-number-of-arrays-with-k-matching-adjacent-elements/
 */
public class CountTheNumberOfArraysWithKMatchingAdjacentElements {

    private static final int MOD = (int)1e9 + 7;
    private static final int UPPER_BOUND = 100_000;
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

    private static long combination(int n, int m) {
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

    // time O(n), space O(n)
    public int countGoodArrays(int n, int m, int k) {
        // https://leetcode.com/problems/count-the-number-of-arrays-with-k-matching-adjacent-elements/editorial/
        return (int) ((((combination(n - 1, k) * m) % MOD) * pow(m - 1, n - k - 1)) % MOD);
    }
}
