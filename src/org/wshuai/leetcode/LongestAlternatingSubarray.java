package org.wshuai.leetcode;

/**
 * Created by Wei on 12/28/2023.
 * #2765 https://leetcode.com/problems/longest-alternating-subarray/
 */
public class LongestAlternatingSubarray {

    // time O(n), space O(1)
    public int alternatingSubarray(int[] nums) {
        int res = -1, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                int curr = 2, j = i + 1, diff = 1;
                for (; j < n && nums[j] - nums[j - 1] == -diff; j++, diff = -diff) {
                    curr++;
                }
                res = Math.max(res, curr);
                i = j - 1;
            }
        }
        return res;
    }
}
