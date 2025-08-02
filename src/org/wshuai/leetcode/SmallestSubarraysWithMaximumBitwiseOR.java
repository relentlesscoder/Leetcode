package org.wshuai.leetcode;

/**
 * Created by Wei on 08/02/2025.
 * #2411 https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/
 */
public class SmallestSubarraysWithMaximumBitwiseOR {

    // time O(n), space O(1)
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] res = new int[n], mask = new int[30];
        for (int i = n - 1, j; i >= 0; i--) {
            j = i;
            for (int k = 0; k < 30; k++) {
                // if current bit is 0 then find the closest num that
                // has this bit set on right
                if ((nums[i] & (1 << k)) == 0) {
                    j = Math.max(j, mask[k]);
                } else { // set the index for current bit
                    mask[k] = i;
                }
            }
            res[i] = j - i + 1;
        }
        return res;
    }
}
