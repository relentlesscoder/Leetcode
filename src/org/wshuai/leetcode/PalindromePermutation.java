package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0266 https://leetcode.com/problems/palindrome-permutation/
 */
public class PalindromePermutation {
	// time O(n)
	public boolean canPermutePalindrome(String s) {
		int[] count = new int[256];
		int odd = 0;
		for(char c : s.toCharArray()){
			count[c]++;
		}
		for(int c : count){
			if(c % 2 == 0){
				continue;
			}
			if(odd == 1){
				return false;
			}
			odd++;
		}
		return true;
	}
}
