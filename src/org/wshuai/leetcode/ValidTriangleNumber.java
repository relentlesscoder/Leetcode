package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/25/2019.
 * #0611 https://leetcode.com/problems/valid-triangle-number/
 */
public class ValidTriangleNumber {
	// time O(n^2)
	// similar question as #259
	public int triangleNumber(int[] nums) {
		int len = nums.length;
		if(len < 3){
			return 0;
		}
		int res = 0;
		Arrays.sort(nums);
		// need to loop from the largest number
		// to pick the largest side (a) for the triangle
		// if a < b + c then obviously a + b > c and a + c > b
		// only need to find all b and c to make a < b + c
		for(int i = len-1; i >= 2; i--){
			int left = 0;
			int right = i-1;
			while(left < right){
				int sum = nums[left] + nums[right];
				if(sum <= nums[i]){
					left++;
				}else{
					//right is the maximum val, if left+right < target
					//then all values (between left and right) + left < target
					res += right-left;
					right--;
				}
			}
		}
		return res;
	}
}
