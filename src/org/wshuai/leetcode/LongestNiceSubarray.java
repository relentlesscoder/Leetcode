package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #2401 https://leetcode.com/problems/longest-nice-subarray/
 */
public class LongestNiceSubarray {

    // time O(n), space O(1)
    public int longestNiceSubarray(int[] nums) {
        int res = 0, n = nums.length;
        // Bitmask denotes the set bits for current sliding window
        for (int i = 0, j = 0, mask = 0; i < n; i++) {
            // Bitmask shares common set bits with nums[i]
            while ((mask & nums[i]) > 0) {
                // Keep removing set bits of nums[j] from the current sliding
                // window until there is no common set bits
                mask ^= nums[j++];
            }
            // Add set bits of nums[i] to the sliding window
            mask |= nums[i];
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
