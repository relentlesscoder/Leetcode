package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2019.
 * #1156 https://leetcode.com/problems/swap-for-longest-repeated-character-substring/
 */
public class SwapForLongestRepeatedCharacterSubstring {

	// time O(n), space O(1)
	public int maxRepOpt1(String text) {
		// Same idea as #0424
		int res = 0, n = text.length();
		int[] freq = new int[26];
		for (char c : text.toCharArray()) {
			freq[c - 'a']++;
		}
		int[] counter = new int[26];
		for (int i = 0, j = 0, maxCount = 0; i < n; i++) {
			int idx = text.charAt(i) - 'a';
			++counter[idx];
			if (freq[idx] > maxCount + 1) {
				maxCount = Math.max(maxCount, counter[idx]);
			}
			while (i - j + 1 - maxCount > 1) {
				--counter[text.charAt(j++) - 'a'];
			}
			res = Math.max(res, i - j + 1);
		}
		return res;
	}
}
