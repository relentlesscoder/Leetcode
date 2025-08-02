package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 08/01/2025.
 * #3487 https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion/
 */
public class MaximumUniqueSubarraySumAfterDeletion {

    // time O(n), space O(n)
    public int maxSum(int[] nums) {
        int res = 0, max = -1000;
        Set<Integer> uniquePositiveNums = new HashSet<>();
        for (int num : nums) {
            if (num > 0 && uniquePositiveNums.add(num)) {
                res += num;
            }
            max = Math.max(num, max);
        }
        // return sum of distinct positive numbers if there is any positive
        // or return the max of the negative numbers
        return uniquePositiveNums.isEmpty() ? max : res;
    }
}
