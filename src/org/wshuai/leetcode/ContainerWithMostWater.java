package org.wshuai.leetcode;

/**
 * Created by Wei on 08/13/2016.
 * #0011 https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {
	// time O(n)
	public int maxArea(int[] height) {
		int n = height.length;
		int left = 0;
		int right = n - 1;
		int res = 0;
		while(left < right){
			int leftHeight = height[left];
			int rightHeight = height[right];
			res = Math.max(res, Math.min(leftHeight, rightHeight) * (right - left));
			// since the shorter side height decides the final area,
			// move the shorter side to create a better solution if possible
			if(leftHeight < rightHeight){
				while(left < right && height[left] <= leftHeight){
					left++;
				}
				/* simpler code below but slower due to unnecessary calculations
				left++;
				 */
			}else{
				while(left < right && height[right] <= rightHeight){
					right--;
				}
				/*
				right--;
				 */
			}
		}
		return res;
	}
}
