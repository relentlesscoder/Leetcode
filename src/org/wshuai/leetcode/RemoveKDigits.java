package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/13/2019.
 * #0402 https://leetcode.com/problems/remove-k-digits/
 */
public class RemoveKDigits {

	// time O(n), space O(n)
	public String removeKdigits(String num, int k) {
		// Corner case : num "9", k 2
		if (k >= num.length()) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
		int n = num.length();
		char[] digits = num.toCharArray();
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n && k > 0; i++) {
			while (k > 0 && !stack.isEmpty() && digits[stack.peek()] > digits[i]) {
				digits[stack.pop()] = '#';
				k--;
			}
			stack.push(i);
		}
		// If all digits are sorted and k is still > 0, remove
		// rest digits from the end
		for (int i = n - 1; i >= 0 & k > 0; i--) {
			if (digits[i] != '#') {
				digits[i] = '#';
				k--;
			}
		}
		for (int i = 0; i < n; i++) {
			if (digits[i] != '#') {
				res.append(digits[i]);
			}
		}
		int j = 0;
		// Remove all leading 0s
		for (; j < res.length() - 1 && res.charAt(j) == '0'; j++) {}
		return res.substring(j);
	}
}
