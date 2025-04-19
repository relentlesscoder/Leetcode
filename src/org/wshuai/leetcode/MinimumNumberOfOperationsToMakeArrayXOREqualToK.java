package org.wshuai.leetcode;

/**
 * Created by Wei on 04/19/2025.
 * #2997 https://leetcode.com/problems/minimum-number-of-operations-to-make-array-xor-equal-to-k/
 */
public class MinimumNumberOfOperationsToMakeArrayXOREqualToK {

    // time O(n), space O(1)
    public int minOperations(int[] nums, int k) {
        int res = 0, xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }
        for (int i = 0; i < 32; i++) {
            if ((((xor >> i) & 1) ^ ((k >> i) & 1)) == 1) {
                res++;
            }
        }
        return res;
    }
}
