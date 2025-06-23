package org.wshuai.leetcode;

/**
 * Created by Wei on 06/22/2025.
 * #3151 https://leetcode.com/problems/special-array-i/
 */
public class SpecialArrayI {

    // time O(n), space O(1)
    public boolean isArraySpecial(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (((nums[i] & 1) ^ (nums[i + 1] & 1)) == 0) {
                return false;
            }
        }
        return true;
    }
}
