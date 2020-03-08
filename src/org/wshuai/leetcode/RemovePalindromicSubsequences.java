package org.wshuai.leetcode;

/**
 * Created by Wei on 01/26/2020.
 * #1332 https://leetcode.com/problems/remove-palindromic-subsequences/
 */
public class RemovePalindromicSubsequences {
	// time O(n)
	public int removePalindromeSub(String s) {
		if(s == null || s.isEmpty()){
			return 0;
		}
		int left = 0, right = s.length() - 1;
		while(left < right){
			if(s.charAt(left++) != s.charAt(right--)){
				return 2;
			}
		}
		return 1;
	}
}
