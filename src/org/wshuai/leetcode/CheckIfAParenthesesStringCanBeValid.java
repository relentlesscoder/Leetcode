package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2023.
 * #2116 https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/
 */
public class CheckIfAParenthesesStringCanBeValid {

	// time O(n), space O(1)
	// #0678 https://leetcode.com/problems/valid-parenthesis-string/
	public boolean canBeValid(String s, String locked) {
		int n = s.length(), balance = 0;
		if (n % 2 == 1) {
			return false;
		}
		for (int i = 0; i < n; i++) {
			if (locked.charAt(i) == '1' && s.charAt(i) == ')') {
				balance--;
			} else {
				balance++;
			}
			if (balance < 0) {
				return false;
			}
		}
		balance = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (locked.charAt(i) == '1' && s.charAt(i) == '(') {
				balance--;
			} else {
				balance++;
			}
			if (balance < 0) {
				return false;
			}
		}
		return true;
	}
}
