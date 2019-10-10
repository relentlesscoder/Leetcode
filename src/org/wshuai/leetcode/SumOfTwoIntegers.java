package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/2016.
 * #371 https://leetcode.com/problems/sum-of-two-integers/
 */
public class SumOfTwoIntegers {
	public int getSum(int a, int b) {
		int result = a ^ b;
		int carry = (a & b) << 1;
		if (carry != 0) {
			return getSum(result, carry);
		}
		return result;
	}
}
