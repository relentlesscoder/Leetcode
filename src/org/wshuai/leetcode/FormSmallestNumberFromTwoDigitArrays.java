package org.wshuai.leetcode;

/**
 * Created by Wei on 01/11/2024.
 * #2605 https://leetcode.com/problems/form-smallest-number-from-two-digit-arrays/
 */
public class FormSmallestNumberFromTwoDigitArrays {

    // time O(m + n), space O(1)
    public int minNumber(int[] nums1, int[] nums2) {
        int min1 = 10, min2 = 10;
        int[] count = new int[10];
        for (int num : nums1) {
            min1 = Math.min(min1, num);
            count[num]++;
        }
        for (int num : nums2) {
            min2 = Math.min(min2, num);
            count[num]++;
        }
        for (int i = 1; i <= 9; i++) {
            if (count[i] == 2) {
                return i;
            }
        }
        return Math.min(min1, min2) * 10 + Math.max(min1, min2);
    }
}
