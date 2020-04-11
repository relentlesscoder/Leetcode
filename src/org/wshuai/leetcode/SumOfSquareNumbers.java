package org.wshuai.leetcode;

/**
 * Created by Wei on 07/29/2017.
 * #0633 https://leetcode.com/problems/sum-of-square-numbers/
 */
public class SumOfSquareNumbers {
	// time O(log(c))
	public boolean judgeSquareSum(int c) {
		int left = 0, right = (int) Math.sqrt(c);
		while (left <= right) {
			int sum = left * left + right * right;
			if (sum == c) {
				return true;
			}
			if (sum < c) {
				left++;
			} else {
				right--;
			}
		}
		return false;
	}
}
