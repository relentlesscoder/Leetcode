package org.wshuai.leetcode;

/**
 * Created by Wei on 01/16/2020.
 * #0125 https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

	// time O(n), space O(1)
	public boolean isPalindrome(String s) {
		int left = 0, right = s.length() - 1;
		while (left <= right) {
			if (!Character.isLetterOrDigit(s.charAt(left))) {
				left++;
			} else if (!Character.isLetterOrDigit(s.charAt(right))) {
				right--;
			} else if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
				return false;
			} else {
				left++;
				right--;
			}
		}
		return true;
	}
}
