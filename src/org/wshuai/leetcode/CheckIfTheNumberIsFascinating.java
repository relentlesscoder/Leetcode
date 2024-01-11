package org.wshuai.leetcode;

/**
 * Created by Wei on 01/11/2024.
 * #2729 https://leetcode.com/problems/check-if-the-number-is-fascinating/
 */
public class CheckIfTheNumberIsFascinating {

    // time O(d), space O(1)
    public boolean isFascinating(int n) {
        int[] nums = new int[] {n, n * 2, n * 3}, map = new int[9];
        for (int num : nums) {
            while (num > 0) {
                int d = num % 10;
                if (d == 0 || ++map[d - 1] > 1) {
                    return false;
                }
                num /= 10;
            }
        }
        for (int c : map) {
            if (c != 1) {
                return false;
            }
        }
        return true;
    }
}
