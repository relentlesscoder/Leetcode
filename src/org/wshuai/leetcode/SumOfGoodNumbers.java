package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #3452 https://leetcode.com/problems/sum-of-good-numbers/
 */
public class SumOfGoodNumbers {

    // time O(n), space O(1)
    public int sumOfGoodNumbers(int[] nums, int k) {
        int sum = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if ((i - k < 0 || nums[i] > nums[i - k])
                    && (i + k >= n || nums[i] > nums[i + k])) {
                sum += nums[i];
            }
        }
        return sum;
    }
}
