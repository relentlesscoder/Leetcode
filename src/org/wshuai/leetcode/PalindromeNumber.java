package org.wshuai.leetcode;

/**
 * Created by Wei on 08/13/2016.
 * #0009 https://leetcode.com/problems/palindrome-number/
 */
public class PalindromeNumber {
	public boolean isPalindrome(int x) {
		if(x < 0){
			return false;
		}
		if(x < 10){
			return true;
		}
		int y = x;
		int rev = 0;
		while(y > 0){
			rev = rev * 10 + y % 10;
			y /= 10;
		}
		return rev == x;
	}
}
