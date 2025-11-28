package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 05/21/2021.
 * #1852 https://leetcode.com/problems/distinct-numbers-in-each-subarray/
 */
public class DistinctNumbersInEachSubarray {

    // time O(n), space O(n)
    public int[] distinctNumbers(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.merge(nums[i], 1, Integer::sum);
            if (i - k + 1 < 0) {
                continue;
            }
            res[i - k + 1] = freq.size();
            int count = freq.get(nums[i - k + 1]) - 1;
            if (count == 0) {
                freq.remove(nums[i - k + 1]);
            } else {
                freq.put(nums[i - k + 1], count);
            }
        }
        return res;
    }
}
