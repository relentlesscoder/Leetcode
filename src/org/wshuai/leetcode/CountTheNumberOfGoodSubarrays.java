package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 04/19/2025.
 * #2537 https://leetcode.com/problems/count-the-number-of-good-subarrays/
 */
public class CountTheNumberOfGoodSubarrays {

    // time O(n), space O(n)
    public long countGood(int[] nums, int k) {
        long res = 0;
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0, j = 0, pairs = 0; i < n; i++) {
            // Let's say the count is k when we increase a number by 1, it
            // actually adds k - 1 new pairs. For example, we have 3 number
            // 5s - let's call them 5a, 5b, 5c. They form total 3 pairs -
            // [5a, 5b], [5a, 5c] and [5b, 5c], by adding the 4th 5 5d we
            // actually add 3 new pairs [5a, 5d], [5b, 5d] and [5c, 5d].
            pairs += freq.merge(nums[i], 1, Integer::sum) - 1;
            while (pairs >= k) {
                // By the same reason, let's say we have k numbers then it
                // will remove k - 1 pairs each time we remove a number.
                pairs -= freq.merge(nums[j++], -1, Integer::sum);
            }
            res += j;
        }
        return res;
    }
}
