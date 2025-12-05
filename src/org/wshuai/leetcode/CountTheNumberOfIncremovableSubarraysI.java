package org.wshuai.leetcode;

/**
 * Created by Wei on 12/05/2025.
 * #2970 https://leetcode.com/problems/count-the-number-of-incremovable-subarrays-i/
 */
public class CountTheNumberOfIncremovableSubarraysI {

    // time O(n), space O(1)
    public int incremovableSubarrayCount(int[] nums) {
        // #2972的值域缩小版
        int res = 0, n = nums.length, right = n - 1;
        while (right > 0 && nums[right - 1] < nums[right]) {
            right--;
        }
        if (right == 0) {
            return n * (n + 1) / 2;
        }
        for (int left = 0; left == 0 || nums[left] > nums[left - 1]; left++) {
            res += n - right + 1;
            while (right < n && nums[left] >= nums[right]) {
                right++;
            }
        }
        res += n - right + 1;
        return res;
    }
}
