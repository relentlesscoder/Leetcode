package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/22/2025.
 * #3132 https://leetcode.com/problems/find-the-integer-added-to-array-ii/
 */
public class FindTheIntegerAddedToArrayII {

    // time O(n * log(n)), space O(log(n))
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        // sort both array,
        // for the first 3 numbers in nums1, it is guaranteed that 1 number
        // will not be removed.
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // enumerate nums1[2] or nums1[1]
        // iterate reversely since the greater the first number in nums1
        // the smaller the diff
        for (int i = 2; i > 0; i--) {
            int diff = nums2[0] - nums1[i], j = 0;
            // from i, check if we can find a nums2[j] == nums1[i] + diff and if so advance.
            // If we can reach the end of nums2 then return the diff.
            for (int k = i; k < nums1.length; k++) {
                if (nums2[j] == nums1[k] + diff && ++j == nums2.length) {
                    return diff;
                }
            }
        }
        // if neither nums1[2] or nums1[1], then nums1[0] is not removed
        return nums2[0] - nums1[0];
    }
}
