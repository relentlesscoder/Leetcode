package org.wshuai.leetcode;

/**
 * Created by Wei on 01/23/2024.
 * #2376 https://leetcode.com/problems/count-special-integers/
 */
public class CountSpecialIntegers {

    // time O(d * 2 * 1024), space O(d * 2 * 1024)
    public int countSpecialNumbers(int n) {
        char[] digits = ("" + n).toCharArray();
        Integer[][][] dp = new Integer[10][2][1024];
        return count(0, 1, 0, digits, dp);
    }

    private int count(int i, int tight, int mask, char[] digits, Integer[][][] dp) {
        if (i == digits.length) {
            return mask == 0 ? 0 : 1;
        }
        if (dp[i][tight][mask] != null) {
            return dp[i][tight][mask];
        }
        int res = 0, end = tight == 1 ? digits[i] - '0' : 9;
        for (int j = 0; j <= end; j++) {
            if ((mask & (1 << j)) > 0) {
                continue;
            }
            int newMask = j == 0 && mask == 0 ? mask : (mask | (1 << j)), newTight = tight == 1 && j == digits[i] - '0' ? 1 : 0;
            res += count(i + 1, newTight, newMask, digits, dp);
        }
        dp[i][tight][mask] = res;
        return res;
    }
}
