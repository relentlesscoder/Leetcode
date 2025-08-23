package org.wshuai.leetcode;

/**
 * Created by Wei on 08/23/2025.
 * #3379 https://leetcode.com/problems/transformed-array/
 */
public class TransformedArray {

    // time O(n), space O(n)
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                res[i] = nums[(i + nums[i]) % n];
            } else if (nums[i] < 0) {
                res[i] = nums[((i + nums[i]) % n + n) % n];
            } else {
                res[i] = nums[i];
            }
        }
        return res;
    }
}
