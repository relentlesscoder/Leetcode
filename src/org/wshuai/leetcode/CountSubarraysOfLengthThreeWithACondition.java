package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #3392 https://leetcode.com/problems/count-subarrays-of-length-three-with-a-condition/
 */
public class CountSubarraysOfLengthThreeWithACondition {

    // time O(n), space O(1)
    public int countSubarrays(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] % 2 == 0 && nums[i - 1] + nums[i + 1] == nums[i] >> 1) {
                res++;
            }
        }
        return res;
    }
}
