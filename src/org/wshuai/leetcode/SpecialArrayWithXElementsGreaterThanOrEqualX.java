package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/04/2020.
 * #1608 https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
 */
public class SpecialArrayWithXElementsGreaterThanOrEqualX {

    // time O(n*log(n))
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < n; i++){
            if(i != 0 && nums[i] == nums[i - 1]){
                continue;
            }
            if(nums[i] >= n - i && (i == 0 || n - i > nums[i - 1])){
                return n - i;
            }
        }
        return -1;
    }
}
