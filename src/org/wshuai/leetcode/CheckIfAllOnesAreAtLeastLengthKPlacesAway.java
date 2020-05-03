package org.wshuai.leetcode;

/**
 * Created by Wei on 05/03/2020.
 * #1437 https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/
 */
public class CheckIfAllOnesAreAtLeastLengthKPlacesAway {
    // time O(n)
    public boolean kLengthApart(int[] nums, int k) {
        int prev = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                continue;
            }
            if(prev == -1 || i - prev >= k + 1){
                prev = i;
            }else{
                return false;
            }
        }
        return true;
    }
}
