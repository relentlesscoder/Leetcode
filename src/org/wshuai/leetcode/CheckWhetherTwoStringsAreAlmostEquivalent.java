package org.wshuai.leetcode;

/**
 * Created by Wei on 09/28/2023.
 * #2068 https://leetcode.com/problems/check-whether-two-strings-are-almost-equivalent/
 */
public class CheckWhetherTwoStringsAreAlmostEquivalent {

	// time O(n), space O(1)
	public boolean checkAlmostEquivalent(String word1, String word2) {
		int[] freq = new int[26];
		for (int i = 0; i < word1.length(); i++) {
			freq[word1.charAt(i) - 'a']++;
			freq[word2.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; i++) {
			if (freq[i] < -3 || freq[i] > 3) {
				return false;
			}
		}
		return true;
	}
}
