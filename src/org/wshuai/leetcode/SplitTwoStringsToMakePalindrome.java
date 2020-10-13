package org.wshuai.leetcode;

/**
 * Created by Wei on 10/13/2020.
 * #1616 https://leetcode.com/problems/split-two-strings-to-make-palindrome/
 */
public class SplitTwoStringsToMakePalindrome {

	// time O(n)
	public boolean checkPalindromeFormation(String a, String b) {
		return check(a, b) || check(b, a);
	}

	private boolean check(String a, String b){
		int n = a.length(), i = 0, j = n - 1;
		while(a.charAt(i) == b.charAt(j)){
			if(++i > --j){
				return true;
			}
		}
		return isPalindrome(a, i, j) || isPalindrome(b, i, j);
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
