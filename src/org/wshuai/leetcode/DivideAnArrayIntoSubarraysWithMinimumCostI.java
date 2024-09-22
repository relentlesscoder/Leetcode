package org.wshuai.leetcode;

/**
 * Created by Wei on 02/10/2024.
 * #3010 https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-i/
 */
public class DivideAnArrayIntoSubarraysWithMinimumCostI {

    // time O(n), space O(1)
    public int minimumCost(int[] nums) {
        int min = 51, secondMin = 51;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                secondMin = min;
                min = nums[i];
            } else if (nums[i] < secondMin) {
                secondMin = nums[i];
            }
        }
        return min + secondMin + nums[0];
    }
}
