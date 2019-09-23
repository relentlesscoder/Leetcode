package org.wshuai.leetcode;

/**
 * Created by Wei on 11/2/2016.
 */
public class SortTransformedArray {
	//https://en.wikipedia.org/wiki/Quadratic_function
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		if (nums == null || nums.length == 0) {
			return new int[0];
		}
		int left = 0;
		int right = nums.length - 1;
		int[] res = new int[nums.length];
		int idx = a >= 0 ? right : left;
		while (right >= left) {
			int lval = calculate(nums[left], a, b, c);
			int rval = calculate(nums[right], a, b, c);
			if (a >= 0) {
				res[idx--] = Math.max(lval, rval);
				if (lval >= rval) {
					left++;
				} else {
					right--;
				}
			} else {
				res[idx++] = Math.min(lval, rval);
				if (lval < rval) {
					left++;
				} else {
					right--;
				}
			}
		}
		return res;
	}

	private int calculate(int val, int a, int b, int c) {
		return val * val * a + val * b + c;
	}
}
