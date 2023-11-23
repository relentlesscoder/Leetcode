package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/22/2023.
 * #2733 https://leetcode.com/problems/neither-minimum-nor-maximum/
 */
public class NeitherMinimumNorMaximum {

    // time O(1), space O(1)
    public int findNonMinOrMax(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return -1;
        }
        int[] temp = new int[] {nums[0], nums[1], nums[2]};
        Arrays.sort(temp);
        return temp[1];
    }
}
