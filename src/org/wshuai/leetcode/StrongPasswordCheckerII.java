package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/29/2023.
 * #2299 https://leetcode.com/problems/strong-password-checker-ii/
 */
public class StrongPasswordCheckerII {

	// time O(n), space O(1)
	public boolean strongPasswordCheckerII(String password) {
		Set<Character> set = new HashSet<>(Arrays.asList(new Character[]{'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'}));
		int n = password.length();
		boolean hasLowercase = false, hasUppercase = false, hasSpecial = false, hasNumber = false;
		if (n < 8) {
			return false;
		}
		for (int i = 0; i < n; i++) {
			char c = password.charAt(i);
			if (c >= 'a' && c <= 'z') {
				hasLowercase = true;
			}
			if (c >= 'A' && c <= 'Z') {
				hasUppercase = true;
			}
			if (c >= '0' && c <= '9') {
				hasNumber = true;
			}
			if (set.contains(c)) {
				hasSpecial = true;
			}
			if (i > 0 && c == password.charAt(i - 1)) {
				return false;
			}
		}
		return hasLowercase && hasUppercase && hasSpecial && hasNumber;
	}
}
