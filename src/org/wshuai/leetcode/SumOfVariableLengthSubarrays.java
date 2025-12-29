package org.wshuai.leetcode;

/**
 * Created by Wei on 12/25/2025.
 * #3427 https://leetcode.com/problems/sum-of-variable-length-subarrays/
 */
public class SumOfVariableLengthSubarrays {

    // time O(n), space O(n)
    public int subarraySum(int[] nums) {
        int res = 0, n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
            int start = Math.max(0, i - nums[i]);
            res += prefix[i + 1] - prefix[start];
        }
        return res;
    }
}
