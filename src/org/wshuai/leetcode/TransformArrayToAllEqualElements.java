package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2025.
 * #3576 https://leetcode.com/problems/transform-array-to-all-equal-elements/
 */
public class TransformArrayToAllEqualElements {

    // time O(n), space O(1)
    public boolean canMakeEqual(int[] nums, int k) {
        return canMakeAllEqualsTo(nums, k, 1)
                || canMakeAllEqualsTo(nums, k, -1);
    }

    private boolean canMakeAllEqualsTo(int[] nums, int k, int val) {
        int opr = 0, prev = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            }
            if (prev != -1) { // we have a previous matching element like -1 at index 0 in [-1, 1, 1, -1]
                // we need minimum of i - j = 3 - 0 = 3 operations to transform to [1, 1, 1, 1]
                // two operations to shift -1 to index 2:
                //   1 -> [1, -1, 1, -1]
                //   2 -> [1, 1, -1, -1]
                // one operation to transform the values:
                //   3 -> [1, 1, 1, 1]
                opr += i - prev;
                prev = -1; // reset prev to -1
            } else {
                prev = i; // set prev to i if we don't have a previous matching element
            }
        }
        return prev == -1 && opr <= k;
    }
}
