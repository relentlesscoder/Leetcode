package org.wshuai.leetcode;

/**
 * Created by Wei on 09/12/2019.
 * #1004 https://leetcode.com/problems/max-consecutive-ones-iii/
 */
public class MaxConsecutiveOnesIII {

    // time O(n), space O(1)
    public int longestOnes(int[] nums, int k) {
        int res = 0, n = nums.length, count = 0;
        for (int i = 0, j = 0; i < n; i++) {
            count += 1 - nums[i];
            while (count > k) {
                count -= 1 - nums[j++];
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
