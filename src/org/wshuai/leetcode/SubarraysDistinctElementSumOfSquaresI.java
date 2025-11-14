package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 11/20/2023.
 * #2913 https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-i/
 */
public class SubarraysDistinctElementSumOfSquaresI {

    // time O(n^2), space O(MAX)
    public int sumCounts(List<Integer> nums) {
        int res = 0, n = nums.size();
        for (int i = 0; i < n; i++) {
            int[] map = new int[101];
            int count = 0;
            for (int j = i; j < n; j++) {
                if (++map[nums.get(j)] == 1) { // use the map to check if the value is new
                    count++;
                }
                res += count * count;
            }
        }
        return res;
    }
}
