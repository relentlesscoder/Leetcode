package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/2023.
 * #2656 https://leetcode.com/problems/maximum-sum-with-exactly-k-elements/
 */
public class MaximumSumWithExactlyKElements {

    // time O(n), space O(1)
    public int maximizeSum(int[] nums, int k) {
        int res = 0, max = 0;
        for (int num : nums) { // To get hte max score just pick the max everytime
            max = Math.max(max, num);
        }
        // score = max * k + (0 + 1 + 2 + ... + k - 1)
        res += max * k;
        res += (k - 1) * (k >> 1) + (k % 2 == 1 ? ((k - 1) >> 1) : 0);
        return res;
    }
}
