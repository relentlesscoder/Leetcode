package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/04/2023.
 * #2089 https://leetcode.com/problems/find-target-indices-after-sorting-array/submissions/
 */
public class FindTargetIndicesAfterSortingArray {

    // time O(n), space O(n)
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        int less = 0, equal = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                equal++;
            }
            if (nums[i] < target) {
                less++;
            }
        }
        for (int i = less; i < equal + less; i++) {
            res.add(i);
        }
        return res;
    }
}
