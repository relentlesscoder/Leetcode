package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 12/26/2023.
 * #2869 https://leetcode.com/problems/minimum-operations-to-collect-elements/
 */
public class MinimumOperationsToCollectElements {

    // time O(n), space O(n)
    public int minOperations(List<Integer> nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = nums.size() - 1; i >= 0; i--) {
            int num = nums.get(i);
            if (num >= 1 && num <= k) {
                set.add(num);
                if (set.size() == k) {
                    return nums.size() - i;
                }
            }
        }
        return 0;
    }
}
