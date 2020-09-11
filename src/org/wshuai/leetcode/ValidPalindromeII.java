package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2019.
 * #0680 https://leetcode.com/problems/valid-palindrome-ii/
 */
public class ValidPalindromeII {

	// time O(n)
	public boolean validPalindrome(String s) {
		int n = s.length();
		for(int i = 0, j = n - 1; i < j; i++, j--){
			if(s.charAt(i) != s.charAt(j)){
				int i1 = i + 1, j1 = j, i2 = i, j2 = j - 1;
				while(i1 < j1 && s.charAt(i1) == s.charAt(j1)){
					i1++;
					j1--;
				}
				while(i2 < j2 && s.charAt(i2) == s.charAt(j2)){
					i2++;
					j2--;
				}
				return (i1 >= j1 || i2 >= j2);
			}
		}
		return true;
	}
}
