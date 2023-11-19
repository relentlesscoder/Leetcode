package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/01/2023.
 * #2357 https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/description/
 */
public class MakeArrayZeroBySubtractingEqualAmounts {

    // time O(n), space O(n)
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            set.add(num);
        }
        return set.size();
    }
}
