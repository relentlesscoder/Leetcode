package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2019.
 * #0680 https://leetcode.com/problems/valid-palindrome-ii/
 */
public class ValidPalindromeII {
	// time O(n)
	public boolean validPalindrome(String s) {
		return isValid(s.toCharArray(), 0, s.length() - 1, 1);
	}

	private boolean isValid(char[] s, int i, int j, int k){
		if(k < 0){
			return false;
		}
		while(i < j && s[i] == s[j]){
			i++;
			j--;
		}
		if(i >= j){
			return true;
		}
		return isValid(s, i + 1, j, k - 1)
				|| isValid(s, i, j - 1, k - 1);
	}
}
