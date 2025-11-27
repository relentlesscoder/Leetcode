package org.wshuai.leetcode;

/**
 * Created by Wei on 09/13/2019.
 * #0402 https://leetcode.com/problems/remove-k-digits/
 */
public class RemoveKDigits {

    // time O(n), space O(n)
	public String removeKdigits(String num, int k) {
		if (k >= num.length()) {
			return "0";
		}
		int n = num.length();
		char[] digits = num.toCharArray();
		StringBuilder stack = new StringBuilder(); // Use StringBuilder as Monotonic stack
		for (int i = 0; i < n; i++) {
			// To ensure there are still enough digits left in [i, n - 1]
			// n - k - SL <= n - i + 1, SL = stack.length()
			// n - k - SL < n - i
			// i < k + SL
			while (!stack.isEmpty() && stack.charAt(stack.length() - 1) > digits[i]
					&& stack.length() + k > i) {
				stack.deleteCharAt(stack.length() - 1);
			}
			// If we have enough digits already, skip the rest
			// e.g. num [1,1,2], k = 1
			if (stack.length() + k < n) {
				stack.append(digits[i]);
			}
		}
		// Remove all leading 0s
		int j = 0;
		for (; j < stack.length() - 1 && stack.charAt(j) == '0'; j++) {}
		return stack.substring(j);
	}
}
