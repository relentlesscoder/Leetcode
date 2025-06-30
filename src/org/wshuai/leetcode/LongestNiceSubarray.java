package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #2401 https://leetcode.com/problems/longest-nice-subarray/
 */
public class LongestNiceSubarray {

    // time O(n), space O(1)
    public int longestNiceSubarray(int[] nums) {
        int res = 0, bitMask = 0; // bitMask denotes the set bits for current sliding window
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (j < i && (bitMask & nums[i]) > 0) { // bitmask shares common set bits with nums[i]
                bitMask -= nums[j++]; // keep removing set bits of nums[j] from the current sliding window
            }
            bitMask |= nums[i]; // add set bits of nums[i] to the sliding window
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
