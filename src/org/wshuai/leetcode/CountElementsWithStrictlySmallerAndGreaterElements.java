package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2024.
 * #2148 https://leetcode.com/problems/count-elements-with-strictly-smaller-and-greater-elements/
 */
public class CountElementsWithStrictlySmallerAndGreaterElements {

    // time O(n), space O(1)
    public int countElements(int[] nums) {
        int res = nums.length, min = (int)1e5 + 2, max = -min;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        for (int num : nums) {
            if (num == min || num == max) {
                res--;
            }
        }
        return res;
    }
}
