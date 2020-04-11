package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2019.
 * #0556 https://leetcode.com/problems/next-greater-element-iii/
 */
public class NextGreaterElementIII {
	// time O(d)
	// same as next permutation #0031
	public int nextGreaterElement(int n) {
		if (n < 10) {
			return -1;
		}
		char[] digits = ("" + n).toCharArray();
		int len = digits.length, left = len - 2;
		while (left >= 0 && digits[left] >= digits[left + 1]) {
			left--;
		}
		if (left == -1) {
			return -1;
		}
		char val = digits[left];
		int right = len - 1;
		while (right > left && digits[right] <= val) {
			right--;
		}
		digits[left] = digits[right];
		digits[right] = val;
		int start = left + 1, end = len - 1;
		while (start < end) {
			char temp = digits[start];
			digits[start++] = digits[end];
			digits[end--] = temp;
		}
		try {
			return Integer.parseInt(new String(digits));
		} catch (Exception ex) {
			return -1;
		}
	}
}
