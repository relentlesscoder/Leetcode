package org.wshuai.leetcode;

/**
 * Created by Wei on 01/16/2020.
 * #0125 https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

	// time O(n), space O(1)
	public boolean isPalindrome(String s) {
		for (int i = 0, j = s.length() - 1; i <= j; ) {
			char left = s.charAt(i), right = s.charAt(j);
			if (!Character.isLetterOrDigit(left)) {
				i++;
			} else if (!Character.isLetterOrDigit(right)) {
				j--;
			} else if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
				return false;
			} else {
				i++;
				j--;
			}
		}
		return true;
	}
}
