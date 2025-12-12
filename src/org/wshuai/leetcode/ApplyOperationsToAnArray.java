package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2024.
 * #2460 https://leetcode.com/problems/apply-operations-to-an-array/
 */
public class ApplyOperationsToAnArray {

    // time O(n), space O(1)
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] <<= 1;
                nums[i + 1] = 0;
            }
        }
        for (int i = 0, j = 0; i < n; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = 0;
                nums[j++] = temp;
            }
        }
        return nums;
    }
}
