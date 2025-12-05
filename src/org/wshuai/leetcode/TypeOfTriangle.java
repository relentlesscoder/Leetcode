package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #3024 https://leetcode.com/problems/type-of-triangle/
 */
public class TypeOfTriangle {

    // time O(1), space O(1)
    public String triangleType(int[] nums) {
        if (nums[0] >= nums[1] + nums[2]
                || nums[1] >= nums[0] + nums[2]
                || nums[2] >= nums[1] + nums[0]) {
            return "none";
        }
        if (nums[0] == nums[1] && nums[1] == nums[2]) {
            return "equilateral";
        }
        if (nums[0] == nums[1]
                || nums[1] == nums[2]
                || nums[0] == nums[2]) {
            return "isosceles";
        }
        return "scalene";
    }
}
