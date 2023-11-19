package org.wshuai.leetcode;

/**
 * Created by Wei on 10/05/2023.
 * #2330 https://leetcode.com/problems/valid-palindrome-iv/
 */
public class ValidPalindromeIV {

	// time O(n), space O(1)
	public boolean makePalindrome(String s) {
		int left = 0, right = s.length() - 1, operation = 0;
		while (left <= right) {
			if (s.charAt(left) != s.charAt(right)) {
				if (operation++ == 2) {
					return false;
				}
			}
			left++;
			right--;
		}
		return true;
	}
}
