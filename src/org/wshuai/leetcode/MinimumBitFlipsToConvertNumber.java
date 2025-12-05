package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/2023.
 * #2220 https://leetcode.com/problems/minimum-bit-flips-to-convert-number/
 */
public class MinimumBitFlipsToConvertNumber {

    // time O(d), space O(1)
    public int minBitFlips(int start, int goal) {
        int res = 0, diff = start ^ goal;
        for (int i = 0; diff != 0; i++) {
            res += (diff & 1);
            diff >>= 1;
        }
        return res;
    }
}
