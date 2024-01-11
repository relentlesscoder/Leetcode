package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/10/2024.
 * #2295 https://leetcode.com/problems/replace-elements-in-an-array/
 */
public class ReplaceElementsInAnArray {

    // time O(n), space O(n)
    public int[] arrayChange(int[] nums, int[][] operations) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }
        for (int[] op : operations) {
            int index = map.get(op[0]);
            nums[index] = op[1];
            map.put(op[1], index);
        }
        return nums;
    }
}
