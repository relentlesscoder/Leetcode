package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0360 https://leetcode.com/problems/sort-transformed-array/
 */
public class SortTransformedArray {
	// time O(n)
	// use the curve to sort, https://en.wikipedia.org/wiki/Quadratic_function
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		if(nums == null || nums.length == 0){
			return new int[0];
		}
		int n = nums.length, left = 0, right = n - 1, index = a >= 0 ? right : left;
		int[] res = new int[n];
		while(left <= right){
			int leftVal = calculate(nums[left], a, b, c);
			int rightVal = calculate(nums[right], a, b, c);
			if(a >= 0){
				res[index--] = Math.max(leftVal, rightVal);
				if(leftVal >= rightVal){
					left++;
				}else{
					right--;
				}
			}else{
				res[index++] = Math.min(leftVal, rightVal);
				if(leftVal < rightVal){
					left++;
				}else{
					right--;
				}
			}
		}
		return res;
	}

	private int calculate(int val, int a, int b, int c){
		return val*val*a + val*b + c;
	}
}
