package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/04/2023.
 * #2006 https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/description/
 */
public class CountNumberOfPairsWithAbsoluteDifferenceK {

    // time O(n), space O(n)
    public int countKDifference(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            res += map.getOrDefault(num, 0);
            map.put(num + k, map.getOrDefault(num + k, 0) + 1);
            map.put(num - k, map.getOrDefault(num - k, 0) + 1);
        }
        return res;
    }
}
