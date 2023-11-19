package org.wshuai.leetcode;

/**
 * Created by Wei on 08/13/2016.
 * #0011 https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {

	// time O(n)
	public int maxArea(int[] height) {
		int res = 0, n = height.length, left = 0, right = n - 1;
		while(left < right){
			int leftHeight = height[left], rightHeight = height[right];
			res = Math.max(res, Math.min(leftHeight, rightHeight) * (right - left));
			// since the shorter side height decides the final area,
			// move the shorter side to create a better solution if possible
			if(height[left] < height[right]){
				while(left < right && height[left] <= leftHeight){
					left++;
				}
			}else{
				while(left < right && height[right] <= rightHeight){
					right--;
				}
			}
		}
		return res;
	}
}
