package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2023.
 * #2083 https://leetcode.com/problems/substrings-that-begin-and-end-with-the-same-letter/
 */
public class SubstringsThatBeginAndEndWithTheSameLetter {

	// time O(n), space O(1)
	public long numberOfSubstrings(String s) {
		long res = 0;
		int[] counter = new int[26];
		for (char c : s.toCharArray()) {
			counter[c - 'a']++;
		}
		for (int i = 0; i < 26; i++) {
			int n = counter[i];
			if (n > 1) {
				res += 1L * (n + 1) * (n >> 1);
			}
			if (n % 2 == 1) {
				res += 1L * ((n + 1) >> 1);
			}
		}
		return res;
	}
}
