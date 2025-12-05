package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2025.
 * #3131 https://leetcode.com/problems/find-the-integer-added-to-array-i/
 */
public class FindTheIntegerAddedToArrayI {

    // time O(n), space O(1)
    public int addedInteger(int[] nums1, int[] nums2) {
        int max1 = -1, max2 = -1;
        for (int num : nums1) {
            max1 = Math.max(max1, num);
        }
        for (int num : nums2) {
            max2 = Math.max(max2, num);
        }
        return max2 - max1;
    }
}
