package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2025.
 * #2787 https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/
 */
public class WaysToExpressAnIntegerAsSumOfPowers {

    private static final long MOD = (int)1e9 + 7;

    // time O(n * x power root of (n)), space O(n)
    public int numberOfWays(int n, int x) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int num = 1; Math.pow(num, x) <= n; num++) {
            int pow = (int) Math.pow(num, x);
            for (int sum = n; sum >= pow; sum--) {
                dp[sum] += dp[sum - pow];
            }
        }
        return (int) (dp[n] % MOD);
    }
}
