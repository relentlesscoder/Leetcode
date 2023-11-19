package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/17/2023.
 * #2294 https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/
 */
public class PartitionArraySuchThatMaximumDifferenceIsK {

    // time O(n * log(n)), space O(1)
    public int partitionArray(int[] nums, int k) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0, j = 0; j < nums.length; j++) {
            if (nums[j] - nums[i] > k) {
                res++;
                i = j;
            }
        }
        return res + 1;
    }
}
