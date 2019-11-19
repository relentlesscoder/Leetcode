package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/19.
 * #1147 https://leetcode.com/problems/longest-chunked-palindrome-decomposition/
 */
public class LongestChunkedPalindromeDecomposition {
	public int longestDecomposition(String text) {
		int res = 0;
		int left = 0;
		int right = text.length() - 1;
		StringBuilder l = new StringBuilder();
		StringBuilder r = new StringBuilder();
		while(left < right){
			l.append(text.charAt(left++));
			r.insert(0, text.charAt(right--));
			if(l.toString().equals(r.toString())){
				res += 2;
				l = new StringBuilder();
				r = new StringBuilder();
			}
		}
		if(l.length() > 0 || left == right){
			return res + 1;
		}
		return res;
	}
}
