package org.wshuai.leetcode;

/**
 * Created by Wei on 01/16/2020.
 * #0125 https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

	// time O(n)
	public boolean isPalindrome(String s) {
		if(s == null || s.isEmpty()){
			return true;
		}
		int n = s.length();
		for(int left = 0, right = n - 1; left <= right; left++, right--){
			while(left < n && getCharacter(s.charAt(left)) == '#'){
				left++;
			}
			while(right >= 0 && getCharacter(s.charAt(right)) == '#'){
				right--;
			}
			if(left <= right && getCharacter(s.charAt(left)) != getCharacter(s.charAt(right))){
				return false;
			}
		}
		return true;
	}

	private char getCharacter(char c){
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
