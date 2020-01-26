package org.wshuai.leetcode;

/**
 * Created by Wei on 01/26/2020.
 * #1328 https://leetcode.com/problems/break-a-palindrome/
 */
public class BreakAPalindrome {
	// time O(n)
	public String breakPalindrome(String palindrome) {
		if(palindrome.length() == 1){
			return "";
		}
		char[] arr = palindrome.toCharArray();
		int n = arr.length, r = (n - 1) / 2 - (n % 2 == 1 ? 1 : 0);
		// if a non 'a' character can be found, replace it with 'a'
		for(int i = 0; i <= r; i++){
			if(arr[i] != 'a'){
				arr[i] = 'a';
				return new String(arr);
			}
		}
		// if all characters are 'a', replace the last character to 'b'
		arr[n - 1] = 'b';
		return new String(arr);
	}
}
