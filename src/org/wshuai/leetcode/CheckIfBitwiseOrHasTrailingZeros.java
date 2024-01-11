package org.wshuai.leetcode;

/**
 * Created by Wei on 01/11/2024.
 * #2980 https://leetcode.com/problems/check-if-bitwise-or-has-trailing-zeros/
 */
public class CheckIfBitwiseOrHasTrailingZeros {

    // time O(n), space O(1)
    public boolean hasTrailingZeros(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {
                if (++count == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}
