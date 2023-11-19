package org.wshuai.leetcode;

/**
 * Created by Wei on 12/21/2020.
 * #1685 https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/
 */
public class SumOfAbsoluteDifferencesInASortedArray {

    // time O(n), space O(n)
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] res = new int[n], prefix = new int[n + 1];
        for(int i = 1; i <= n; i++){
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for(int i = 0; i < n; i++){
            // sum(nums[i] - nums[j]) (for all j < i) + sum(nums[k] - nums[i]) (for all k > i)
            // nums[i] * i - sum(0 -> i - 1) + sum(i + 1, n - 1) - nums[i] * (n - i - 1)
            res[i] = nums[i] * (2 * i - n + 1) - prefix[i] + prefix[n] - prefix[i + 1];
        }
        return res;
    }
}
