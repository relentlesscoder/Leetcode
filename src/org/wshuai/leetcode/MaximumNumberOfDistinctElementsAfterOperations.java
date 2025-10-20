package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/20/2025.
 * #3397 https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/
 */
public class MaximumNumberOfDistinctElementsAfterOperations {

    // time O(n * log(n)), space O(1)
    public int maxDistinctElements(int[] nums, int k) {
        // https://leetcode.com/problems/maximum-number-of-distinct-elements-after-operations/editorial/
        Arrays.sort(nums);
        int res = 0, prev = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // Greedy: check if we can use minimum value prev + 1
            // from [nums[i] - k, nums[i] + k]
            int curr = Math.min(Math.max(nums[i] - k, prev + 1), nums[i] + k);
            if (curr > prev) {
                prev = curr;
                res++;
            }
        }
        return res;
    }
}
