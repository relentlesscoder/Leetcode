package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/09/2015.
 * #0001 https://leetcode.com/problems/two-sum/
 */
public class TwoSum {

    // time O(n), space O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int index = map.getOrDefault(target - nums[i], -1);
            if (index != -1) {
                return new int[]{index, i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}

