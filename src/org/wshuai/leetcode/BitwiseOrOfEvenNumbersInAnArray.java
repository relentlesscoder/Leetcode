package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2025.
 * #3688 https://leetcode.com/problems/bitwise-or-of-even-numbers-in-an-array/
 */
public class BitwiseOrOfEvenNumbersInAnArray {

    // time O(n), space O(1)
    public int evenNumberBitwiseORs(int[] nums) {
        int res = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {
                res |= num;
            }
        }
        return res;
    }
}
