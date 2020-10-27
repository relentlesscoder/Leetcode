package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2019.
 * #0680 https://leetcode.com/problems/valid-palindrome-ii/
 */
public class ValidPalindromeII {

	// time O(n)
	public boolean validPalindrome(String s) {
		int n = s.length();
		for(int i = 0, j = n - 1; i <= j; i++, j--){
			if(s.charAt(i) != s.charAt(j)){
				return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
			}
		}
		return true;
	}

	private boolean isPalindrome(String s, int i, int j){
		while(i < j){
			if(s.charAt(i++) != s.charAt(j--)){
				return false;
			}
		}
		return true;
	}
}
