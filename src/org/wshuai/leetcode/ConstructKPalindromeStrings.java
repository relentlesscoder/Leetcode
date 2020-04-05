package org.wshuai.leetcode;

/**
 * Created by Wei on 04/05/2020.
 * #1400 https://leetcode.com/problems/construct-k-palindrome-strings/
 */
public class ConstructKPalindromeStrings {
	// time O(n)
	public boolean canConstruct(String s, int k) {
		int n = s.length(), odd = 0;
		if(n < k){
			return false;
		}
		int[] count = new int[26];
		for(char c : s.toCharArray()){
			count[c - 'a']++;
		}
		for(int i = 0; i < 26; i++){
			if(count[i] % 2 == 1){
				odd++;
			}
		}
		// the count of characters that have odd count
		// must be less than or equal to k so that the
		// extra 1 character can be put in the middle to
		// form a palindrome.
		return odd <= k;
	}
}
