package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/13/2021.
 * #1708 https://leetcode.com/problems/largest-subarray-length-k/
 */
public class LargestSubarrayLengthK {

    // time O(n)
    public int[] largestSubarray(int[] nums, int k) {
        int max = Integer.MIN_VALUE, start = -1;
        for(int i = 0; i <= nums.length - k; i++){
            if(nums[i] > max){
                max = nums[i];
                start = i;
            }
        }
        return Arrays.copyOfRange(nums, start, start + k);
    }
}
