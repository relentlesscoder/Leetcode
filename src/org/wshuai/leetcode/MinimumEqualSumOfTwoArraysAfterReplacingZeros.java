package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #2918 https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/
 */
public class MinimumEqualSumOfTwoArraysAfterReplacingZeros {

    // time O(n), space O(1)
    public long minSum(int[] nums1, int[] nums2) {
        long zero1 = 0, zero2 = 0, sum1 = 0, sum2 = 0;
        for (int num : nums1) {
            zero1 += num == 0 ? 1 : 0;
            sum1 += num;
        }
        for (int num : nums2) {
            zero2 += num == 0 ? 1 : 0;
            sum2 += num;
        }
        // since all zeros needs to be replaced by strictly positive number
        // so all zeros must have value at least equals to 1 at the end
        // if the side with smaller sum does not have any zero (to increase)
        // then it is impossible to get equal sum
        if (sum1 + zero1 > sum2 + zero2 && zero2 == 0) {
            return -1;
        } else if (sum1 + zero1 < sum2 + zero2 && zero1 == 0) {
            return -1;
        }
        // the minimum sum is the greater sum between the two
        return Math.max(sum1 + zero1, sum2 + zero2);
    }
}
