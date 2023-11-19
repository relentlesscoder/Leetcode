package org.wshuai.leetcode;

/**
 * Created by Wei on 05/22/2021.
 * #1848 https://leetcode.com/problems/minimum-distance-to-the-target-element/
 */
public class MinimumDistanceToTheTargetElement {

    // time O(n)
    public int getMinDistance(int[] nums, int target, int start) {
        int dist = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                dist = Math.min(dist, Math.abs(i - start));
            }
        }
        return dist;
    }
}
