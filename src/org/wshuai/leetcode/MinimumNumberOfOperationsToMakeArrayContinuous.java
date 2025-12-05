package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/11/2023.
 * #2009 https://leetcode.com/problems/minimum-number-of-operations-to-make-array-continuous/
 */
public class MinimumNumberOfOperationsToMakeArrayContinuous {

    // time O(n * log(n)), space O(n)
    public int minOperations(int[] nums) {
        // We need to know the minimum numbers we need to replace to make
        // the array continuous, we can count for each of the number nums[i],
        // how many valid numbers we already have in range [nums[i], nums[i]
        // + n - 1]. We can first sort the array and deduplicate it, for each
        // of the numbers we can maintain a sliding window and keep extending
        // the right end until the left end of the window is too small -
        // nums[left] < nums[right] - n + 1. We can get the maximum count of
        // existing numbers for all windows max(right - left + 1), the result
        // is n - max(right - left + 1).
        int n = nums.length, m = 1, res = 0;
        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[m++] = nums[i]; // Deduplicate
            }
        }
        for (int left = 0, right = 0; right < m; right++) {
            while (nums[left] < nums[right] - n + 1) {
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return n - res;
    }
}
