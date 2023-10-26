package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2023.
 * #1935 https://leetcode.com/problems/maximum-number-of-words-you-can-type/
 */
public class MaximumNumberOfWordsYouCanType {

	// time O(n), space O(1)
	public int canBeTypedWords(String text, String brokenLetters) {
		int res = 0;
		int[] broken = new int[26];
		for (char c : brokenLetters.toCharArray()) {
			broken[c - 'a'] = 1;
		}
		char mask = '#';
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c == ' ') {
				res += mask == '#' ? 1 : 0;
				mask = '#';
			} else if (broken[c - 'a'] == 1) {
				mask = c;
			}
		}
		res += mask == '#' ? 1 : 0;
		return res;
	}
}
