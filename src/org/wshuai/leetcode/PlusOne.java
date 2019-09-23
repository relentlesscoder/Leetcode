package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #66 https://leetcode.com/problems/plus-one/
 */
public class PlusOne {
	public int[] plusOne(int[] digits) {
		if (digits == null || digits.length == 0) {
			return digits;
		}
		int len = digits.length;
		int carry = 0;
		boolean ext = false;
		for (int i = len - 1; i >= 0; i--) {
			int r = digits[i] + carry;
			if (i == len - 1) {
				r++;
			}
			digits[i] = r < 10 ? r : r - 10;
			carry = r >= 10 ? 1 : 0;
			if (i == 0 && carry > 0) {
				ext = true;
			}
		}
		if (!ext) {
			return digits;
		} else {
			int[] arr = new int[len + 1];
			System.arraycopy(digits, 0, arr, 1, len);
			arr[0] = 1;
			return arr;
		}
	}
}
