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
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < nums.length; j++) {
            count += map.getOrDefault(nums[j], 0); // each nums[j] adds map.get(nums[j]) equal pairs
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            while (count >= k) {
                map.put(nums[i], map.get(nums[i]) - 1); // each nums[i] removes map.get(nums[i]) equal pairs
                count -= map.get(nums[i++]);
            }
            res += i; // for each index i' left to i, [i', j] is a good subarray
        }
        return res;
    }
}
