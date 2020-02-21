package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2016.
 * #0409 https://leetcode.com/problems/longest-palindrome/
 */
public class LongestPalindrome {
	// time O(n)
	public int longestPalindrome(String s) {
		int[] count = new int[128];
		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i)]++;
		}
		int res = 0;
		for (int c : count) {
			if (c == 0) {
				continue;
			}
			res += c % 2 == 0 ? c : c - 1;
		}
		return res + (s.length() > res ? 1 : 0);
	}
}
