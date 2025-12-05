package org.wshuai.leetcode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 09/27/2023.
 * #2824 https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/
 */
public class CountPairsWhoseSumIsLessThanTarget {

    // time O(n * log(n)), space O(1)
    public int countPairs(List<Integer> nums, int target) {
        int res = 0, n = nums.size();
        Collections.sort(nums);
        for (int i = 0, j = n - 1; i < j; ) {
            if (nums.get(i) + nums.get(j) < target) {
                res += j - i;
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
