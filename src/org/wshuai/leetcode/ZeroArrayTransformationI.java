package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #3355 https://leetcode.com/problems/zero-array-transformation-i/
 */
public class ZeroArrayTransformationI {

    // time O(n), space O(n)
    public boolean isZeroArray(int[] nums, int[][] queries) {
        // 差分数组应用
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int[] q : queries) {
            diff[q[0]]--;
            diff[q[1] + 1]++;
        }
        for (int i = 0, sum = 0; i < n; i++) {
            sum += diff[i];
            if (nums[i] + sum > 0) {
                return false;
            }
        }
        return true;
    }
}
