package org.wshuai.leetcode;

/**
 * Created by Wei on 04/27/2021.
 * #1800 https://leetcode.com/problems/maximum-ascending-subarray-sum/
 */
public class MaximumAscendingSubarraySum {

    // time O(n)
    public int maxAscendingSum(int[] nums) {
        int res = nums[0], sum = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                sum += nums[i];
            }else{
                sum = nums[i];
            }
            res = Math.max(sum, res);
        }
        return res;
    }

}
