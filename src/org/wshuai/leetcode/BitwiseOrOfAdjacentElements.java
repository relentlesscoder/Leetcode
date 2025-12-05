package org.wshuai.leetcode;

/**
 * Created by Wei on 06/22/2025.
 * #3173 https://leetcode.com/problems/bitwise-or-of-adjacent-elements/
 */
public class BitwiseOrOfAdjacentElements {

    // time O(n), space O(1)
    public int[] orArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            res[i] = (nums[i] | nums[i + 1]);
        }
        return res;
    }
}
