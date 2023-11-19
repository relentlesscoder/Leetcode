package org.wshuai.leetcode;

/**
 * Created by Wei on 09/01/2023.
 * #2340 https://leetcode.com/problems/minimum-adjacent-swaps-to-make-a-valid-array/description/
 */
public class MinimumAdjacentSwapsToMakeAValidArray {

    // time O(n), space O(1)
    public int minimumSwaps(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int res = 0, min = Integer.MAX_VALUE, max = 0, minIndex = 0, maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }
            if (nums[i] >= max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        // if min index is greater than max index,
        // one swap will be saved for moving min to leftmost position
        // when moving the max to the rightmost position
        return nums.length - 1 - maxIndex + minIndex + (minIndex > maxIndex ? -1 : 0);
    }
}
