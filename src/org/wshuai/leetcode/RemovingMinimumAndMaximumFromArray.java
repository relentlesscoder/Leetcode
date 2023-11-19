package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/2023.
 * #2091 https://leetcode.com/problems/removing-minimum-and-maximum-from-array/
 */
public class RemovingMinimumAndMaximumFromArray {

    // time O(n), space O(1)
    public int minimumDeletions(int[] nums) {
        int n = nums.length, min = 100_001, max = -100_001, minIndex = -1, maxIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }
        }
        int left = Math.min(minIndex, maxIndex), right = Math.max(minIndex, maxIndex);
        // There are 3 possible ways to delete min and max: delete from both sides, delete both from left or delete both from right
        return Math.min(left + 1 + n - right, Math.min(right + 1, n - left));
    }
}
