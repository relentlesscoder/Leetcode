package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 01/06/2024.
 * #2229 https://leetcode.com/problems/check-if-an-array-is-consecutive/
 */
public class CheckIfAnArrayIsConsecutive {

    // time O(n), space O(n)
    public boolean isConsecutive(int[] nums) {
        int min = Integer.MAX_VALUE, n = nums.length;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        Set<Integer> unique = new HashSet<>();
        for (int num : nums) {
            if (num >= min + n || unique.contains(num)) {
                return false;
            }
            unique.add(num);
        }
        return true;
    }
}
