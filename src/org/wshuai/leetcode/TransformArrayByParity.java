package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #3467 https://leetcode.com/problems/transform-array-by-parity/
 */
public class TransformArrayByParity {

    // time O(n), space O(1)
    public int[] transformArray(int[] nums) {
        int n = nums.length, j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                nums[j++] = 0;
            }
        }
        for (; j < n; j++) {
            nums[j] = 1;
        }
        return nums;
    }
}
