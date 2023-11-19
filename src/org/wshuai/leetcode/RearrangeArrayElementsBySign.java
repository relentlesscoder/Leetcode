package org.wshuai.leetcode;

/**
 * Created by Wei on 09/11/2023.
 * #2149 https://leetcode.com/problems/rearrange-array-elements-by-sign/description/
 */
public class RearrangeArrayElementsBySign {

    // time O(n), space O(n)
    public int[] rearrangeArray(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res[j] = nums[i];
                j += 2;
            }
        }
        for (int i = 0, j = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                res[j] = nums[i];
                j += 2;
            }
        }
        return res;
    }
}
