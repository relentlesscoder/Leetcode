package org.wshuai.leetcode;

/**
 * Created by Wei on 12/29/2023.
 * #2206 https://leetcode.com/problems/divide-array-into-equal-pairs/
 */
public class DivideArrayIntoEqualPairs {

    // time O(n), space O(1)
    public boolean divideArray(int[] nums) {
        int[] count = new int[501];
        for (int num : nums) {
            count[num]++;
        }
        for (int i = 1; i <= 500; i++) {
            if (count[i] != 0 && count[i] % 2 == 1) {
                return false;
            }
        }
        return true;
    }
}
