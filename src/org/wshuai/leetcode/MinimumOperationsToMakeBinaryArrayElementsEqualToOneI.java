package org.wshuai.leetcode;

/**
 * Created by Wei on 06/22/2025.
 * #3191 https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/
 */
public class MinimumOperationsToMakeBinaryArrayElementsEqualToOneI {

    // time O(n), space O(1)
    public int minOperations(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 2; i < n; i++) {
            if (nums[i - 2] == 0) {
                res++;
                nums[i - 2] = nums[i - 2] ^ 1;
                nums[i - 1] = nums[i - 1] ^ 1;
                nums[i] = nums[i] ^ 1;
            }
        }
        return nums[n - 1] == 1 && nums[n - 2] == 1 ? res : -1;
    }
}
