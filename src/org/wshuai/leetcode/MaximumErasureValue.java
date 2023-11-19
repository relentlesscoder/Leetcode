package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 01/16/2021.
 * #1695 https://leetcode.com/problems/maximum-erasure-value/
 */
public class MaximumErasureValue {

    // time O(n), space O(n)
    public int maximumUniqueSubarray(int[] nums) {
        int res = 0, sum = 0;
        Set<Integer> used = new HashSet<>();
        for(int i = 0, j = 0; j < nums.length; j++){
            while(used.contains(nums[j])){
                used.remove(nums[i]);
                sum -= nums[i++];
            }
            used.add(nums[j]);
            sum += nums[j];
            res = Math.max(res, sum);
        }
        return res;
    }
}
