package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/15/2025.
 * #LCCI-16.24 https://leetcode.cn/problems/pairs-with-sum-lcci/
 */
public class PairsWithSumLCCI {

    // time O(n), space O(n)
    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int count = map.getOrDefault(target - nums[i], 0);
            if (count > 0) {
                res.add(Arrays.asList(new Integer[]{target - nums[i], nums[i]}));
                map.put(target - nums[i], --count);
            } else {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
        }
        return res;
    }
}
