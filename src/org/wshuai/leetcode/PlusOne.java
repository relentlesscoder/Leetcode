package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0066 https://leetcode.com/problems/plus-one/
 */
public class PlusOne {
	// time O(n)
	public int[] plusOne(int[] digits) {
		int carry = 1, n = digits.length;
		for(int i = n - 1; i >= 0; i--){
			int sum = digits[i] + carry;
			digits[i] = sum % 10;
			carry = sum /= 10;
		}
		int size = n + (carry > 0 ? 1 : 0);
		int[] res = new int[size];
		int j = size - 1;
		for(int i = n - 1; i >= 0; i--){
			res[j--] = digits[i];
		}
		if(carry > 0){
			res[0] = carry;
		}
		return res;
	}
}
