package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/03/2025.
 * #2999 https://leetcode.com/problems/count-the-number-of-powerful-integers/
 */
public class CountTheNumberOfPowerfulIntegers {

    // time O(n * D), space O(n)
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        String low = Long.toString(start);
        String high = Long.toString(finish);
        int n = high.length();
        low = "0".repeat(n - low.length()) + low;
        long[][][] memo = new long[n][2][2];
        for (long[][] a : memo) {
            for (long[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, true, true, low.toCharArray(), high.toCharArray(), limit, s.toCharArray(), memo);
    }

    private long dfs(int i, boolean limitLow, boolean limitHigh, char[] low, char[] high, int limit, char[] s, long[][][] memo) {
        if (i == high.length) {
            return 1;
        }
        int ll = limitLow ? 1 : 0;
        int lh = limitHigh ? 1 : 0;
        if (memo[i][ll][lh] != -1) {
            return memo[i][ll][lh];
        }
        int l = limitLow ? low[i] - '0' : 0;
        int h = limitHigh ? high[i] - '0' : 9;
        long res = 0;
        if (i < high.length - s.length) {
            for (int d = l; d <= Math.min(h, limit); d++) {
                res += dfs(i + 1, limitLow && d == l, limitHigh && d == h, low, high, limit, s, memo);
            }
        } else {
            int x = s[i - (high.length - s.length)] - '0';
            if (l <= x && x <= h) {
                res = dfs(i + 1, limitLow && x == l, limitHigh && x == h, low, high, limit, s, memo);
            }
        }
        return memo[i][ll][lh] = res;
    }
}
