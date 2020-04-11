package org.wshuai.leetcode;

/**
 * Created by Wei on 03/23/2020.
 * #1392 https://leetcode.com/problems/longest-happy-prefix/
 */
public class LongestHappyPrefix {
	// time O(n), space O(n)
	public String longestPrefix(String s) {
		int n = s.length();
		int[] lsp = new int[n];
		lsp[0] = 0;
		int j = 0;
		for(int i = 1; i < s.length(); i++){
			while(j > 0 && s.charAt(i) != s.charAt(j)){
				j = lsp[j - 1];
			}
			if(s.charAt(i) == s.charAt(j)){
				j++;
			}
			lsp[i] = j;
		}
		return lsp[n - 1] > 0 ? s.substring(0, lsp[n - 1]) : "";
	}
}
