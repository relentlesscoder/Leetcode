package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/18/2016.
 * #0454 https://leetcode.com/problems/4sum-ii/
 */
public class FourSumII {

    // time O(N^2), space O(N^2)
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                map.merge(num1 + num2, 1, Integer::sum);
            }
        }
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                res += map.getOrDefault(-num3 - num4, 0);
            }
        }
        return res;
    }
}
