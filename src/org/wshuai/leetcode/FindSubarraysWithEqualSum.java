package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 01/09/2024.
 * #2395 https://leetcode.com/problems/find-subarrays-with-equal-sum/
 */
public class FindSubarraysWithEqualSum {

    // time O(n), space O(n)
    public boolean findSubarrays(int[] nums) {
        int n = nums.length;
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            int sum = nums[i] + nums[i + 1];
            if (!seen.add(sum)) {
                return true;
            }
        }
        return false;
    }
}
