package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/19/2019.
 * #0976 https://leetcode.com/problems/largest-perimeter-triangle/
 */
public class LargestPerimeterTriangle {

    // time O(n), space O(1)
    public int largestPerimeter(int[] nums) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int c = n - 1; c >= 2; c--) {
            if (nums[c - 1] + nums[c - 2] > nums[c]) {
                res = Math.max(res, nums[c - 1] + nums[c - 2] + nums[c]);
                break;
            }
        }
        return res;
    }
}
