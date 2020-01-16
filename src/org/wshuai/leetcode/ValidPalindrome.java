package org.wshuai.leetcode;

/**
 * Created by Wei on 01/16/2020.
 * #0125 https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {
	// time O(n)
	public boolean isPalindrome(String s) {
		int n = s.length(), i = 0, j = n - 1;
		if(n == 0){
			return true;
		}
		char[] arr = s.toLowerCase().toCharArray();
		while(i < j){
			char l = arr[i], r = arr[j];
			if(!isAlphaNumeric(l)){
				i++;
			}else if(!isAlphaNumeric(r)){
				j--;
			}else if(l != r){
				return false;
			}else{
				i++;
				j--;
			}
		}
		return true;
	}

	private boolean isAlphaNumeric(char c){
		return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z');
	}
}
