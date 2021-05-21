package org.wshuai.leetcode;

/**
 * Created by Wei on 05/21/2021.
 * #1827 https://leetcode.com/problems/minimum-operations-to-make-the-array-increasing/
 */
public class MinimumOperationsToMakeTheArrayIncreasing {

    // time O(n)
    public int minOperations(int[] nums) {
        int res = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i - 1]){
                continue;
            }
            int target = nums[i - 1] + 1;
            res += target - nums[i];
            nums[i] = target;
        }
        return res;
    }
}
