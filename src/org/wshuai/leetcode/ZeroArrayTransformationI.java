package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #3355 https://leetcode.com/problems/zero-array-transformation-i/
 */
public class ZeroArrayTransformationI {

    // time O(n), space O(n)
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int len = nums.length, sum = 0;
        int[] count = new int[len + 1];
        for (int[] query: queries) {
            count[query[0]]++;
            count[query[1] + 1]--;
        }
        for (int i = 0; i < len; i++) {
            sum += count[i];
            if (sum < nums[i]) {
                return false;
            }
        }
        return true;
    }
}
