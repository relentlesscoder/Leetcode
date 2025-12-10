package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/2016.
 * #0027 https://leetcode.com/problems/remove-element/
 */
public class RemoveElement {

    // time O(n), space O(1)
    public int removeElement(int[] nums, int val) {
        int n = nums.length, j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
