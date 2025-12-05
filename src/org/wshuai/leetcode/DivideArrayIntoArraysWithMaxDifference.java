package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/28/2023.
 * #2966 https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/
 */
public class DivideArrayIntoArraysWithMaxDifference {

    // time O(n * log(n)), space O(n)
    public int[][] divideArray(int[] nums, int k) {
        int[][] res = new int[nums.length / 3][3];
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < nums.length; i += 3, j++) {
            if (nums[i + 2] - nums[i] > k) {
                return new int[0][];
            }
            res[j] = new int[]{nums[i], nums[i + 1], nums[i + 2]};
        }
        return res;
    }
}
