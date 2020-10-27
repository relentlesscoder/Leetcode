package org.wshuai.leetcode;

/**
 * Created by Wei on 01/16/2020.
 * #0125 https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

	// time O(n)
	public boolean isPalindrome(String s) {
		if(s == null || s.length() == 0){
			return true;
		}
		for(int i = 0, j = s.length() - 1; i <= j; i++, j--){
			while(i < j && parseCharacter(s.charAt(i)) == '#'){
				i++;
			}
			while(i < j && parseCharacter(s.charAt(j)) == '#'){
				j--;
			}
			if(i <= j && parseCharacter(s.charAt(i)) != parseCharacter(s.charAt(j))){
				return false;
			}
		}
		return true;
	}

	private char parseCharacter(char c){
		if(c >= 'a' && c <= 'z'){
			return c;
		}
		if(c >= 'A' && c <= 'Z'){
			return (char)('a' + (c - 'A'));
		}
		if(c >= '0' && c <= '9'){
			return c;
		}
		return '#';
	}
}
