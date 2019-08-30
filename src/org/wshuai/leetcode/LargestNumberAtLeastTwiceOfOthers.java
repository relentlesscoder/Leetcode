package org.wshuai.leetcode;

/**
 * Created by Wei on 8/30/2019.
 * #747 https://leetcode.com/problems/largest-number-at-least-twice-of-others/
 */
public class LargestNumberAtLeastTwiceOfOthers {
    public int dominantIndex(int[] nums) {
        int max = nums[0];
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > max){
                max = nums[i];
                res = i;
            }
            nums[i] *= 2;
        }
        for(int i = 0; i < nums.length; i++){
            if(i != res && nums[i] > max){
                return -1;
            }
        }
        return res;
    }
}
