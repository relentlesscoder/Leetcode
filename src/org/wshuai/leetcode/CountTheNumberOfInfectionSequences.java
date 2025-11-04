package org.wshuai.leetcode;

/**
 * Created by Wei on 11/04/2025.
 * #2954 https://leetcode.com/problems/count-the-number-of-infection-sequences/
 */
public class CountTheNumberOfInfectionSequences {

    private static final int MOD = (int)1e9 + 7;

    private static final int UPPER_BOUND = 100_000;

    private static final long[] FACTORIAL = new long[UPPER_BOUND];

    private static final long[] INV_FACTORIAL = new long[UPPER_BOUND];

    static {
        FACTORIAL[0] = 1;
        for (int i = 1; i < UPPER_BOUND; i++) {
            FACTORIAL[i] = FACTORIAL[i - 1] * i % MOD;
        }
        INV_FACTORIAL[UPPER_BOUND - 1] = pow(FACTORIAL[UPPER_BOUND - 1], MOD - 2);
        for (int i = UPPER_BOUND - 1; i > 0; i--) {
            INV_FACTORIAL[i - 1] = INV_FACTORIAL[i] * i % MOD;
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

    private static long combination (int n, int m) {
        return FACTORIAL[n] * INV_FACTORIAL[m] % MOD * INV_FACTORIAL[n - m] % MOD;
    }

    // time O(m), space O(1)
    public int numberOfSequence(int n, int[] sick) {
        // https://leetcode.cn/problems/count-the-number-of-infection-sequences/solutions/2551734/zu-he-shu-xue-ti-by-endlesscheng-5fjp/
        int m = sick.length, total = n - m;
        long res = combination(total, sick[0]) * combination(total - sick[0], n - sick[m - 1] - 1) % MOD;
        total -= sick[0] + n - sick[m - 1] - 1;
        int e = 0;
        for (int i = 1; i < m; i++) {
            int count = sick[i] - sick[i - 1] - 1;
            if (count > 0) {
                e += count - 1;
                res = res * combination(total, count) % MOD;
                total -= count;
            }
        }
        return (int) (res * pow(2, e) % MOD);
    }
}
