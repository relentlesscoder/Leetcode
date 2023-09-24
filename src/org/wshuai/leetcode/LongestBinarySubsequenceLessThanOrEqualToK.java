package org.wshuai.leetcode;

/**
 * Created by Wei on 09/23/2023.
 * #2311 https://leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k/
 */
public class LongestBinarySubsequenceLessThanOrEqualToK {

	// time O(n), space O(1)
	public int longestSubsequence(String s, int k) {
		int res = 0, n = s.length(), curr = 0, pow = 1, i = n - 1;
		// For example when k = 110 ("1101110") s = "10001101011010011",
		// we know that the last subsequence (without leading 0) will not be longer
		// than 7. We can't make the subsequence longer from skipping any character
		// in it and choosing a '1' from the left to replace it, and we might make the
		// final subsequence shorter with less leading 0s. So the optimal solution is that
		// we take the rightmost n (or n - 1 if the value is greater than k) characters and
		// add all the leading 0s.
		for ( ; i >= 0 && curr + pow <= k; i--) {
			if (s.charAt(i) == '1') {
				curr += pow;
			}
			res++; // take whatever
			pow <<= 1;
		}
		for (int j = 0; j <= i; j++) {
			res += s.charAt(j) == '0' ? 1 : 0; // take leading zeros
		}
		return res;
	}
}
