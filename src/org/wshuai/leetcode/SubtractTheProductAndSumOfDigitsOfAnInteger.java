package org.wshuai.leetcode;

/**
 * Created by Wei on 12/9/2019.
 * #1281 https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 */
public class SubtractTheProductAndSumOfDigitsOfAnInteger {
	public int subtractProductAndSum(int n) {
		int sum = 0;
		int prod = 1;
		while(n > 0){
			int digit = n % 10;
			sum += digit;
			prod *= digit;
			n /= 10;
		}
		return prod - sum;
	}
}
