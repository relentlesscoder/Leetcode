package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/18/2023.
 * #1885 https://leetcode.com/problems/count-pairs-in-two-arrays/
 */
public class CountPairsInTwoArrays {

    // time O(n * log(n)), space O(1)
    public long countPairs(int[] nums1, int[] nums2) {
        // nums1[i] + nums1[j] > nums2[i] + nums2[j] equals (nums1[i] - nums2[i]) + (nums1[j] - nums2[j]) > 0
        // thus we need to build a new array (here I reuse nums1 to save some space) to record nums1[i] - nums2[i]
        // for each index i. Then we sort the diff array and use two pointers to get all the pairs with positive sum.
        // Please note that for diff array the restriction i < j in the original problem is not important.
        long res = 0;
        int n = nums1.length, left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            nums1[i] -= nums2[i];
        }
        Arrays.sort(nums1);
        while (left < right) {
            if (nums1[left] + nums1[right] <= 0) {
                left++;
            } else {
                res += right - left;
                right--;
            }
        }
        return res;
    }
}
