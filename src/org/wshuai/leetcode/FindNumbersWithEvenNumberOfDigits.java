package org.wshuai.leetcode;

/**
 * Created by Wei on 12/23/19.
 * #1295 https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
 */
public class FindNumbersWithEvenNumberOfDigits {
	public int findNumbers(int[] nums) {
		int res = 0;
		for(int n : nums){
			if(countDigit(n) % 2 == 0){
				res++;
			}
		}
		return res;
	}

	private int countDigit(int n){
		int res = 0;
		while(n > 0){
			n /= 10;
			res++;
		}
		return res;
	}
}
