package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/2023.
 * #1913 https://leetcode.com/problems/maximum-product-difference-between-two-pairs/
 */
public class MaximumProductDifferenceBetweenTwoPairs {

    // time O(n), space O(1)
    public int maxProductDifference(int[] nums) {
        int max = 0, secondMax = 0, min = 10_001, secondMin = 10_001;
        for (int num : nums) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax) {
                secondMax = num;
            }
            if (num < min) {
                secondMin = min;
                min = num;
            } else if (num < secondMin) {
                secondMin = num;
            }
        }
        return max * secondMax - min * secondMin;
    }
}
