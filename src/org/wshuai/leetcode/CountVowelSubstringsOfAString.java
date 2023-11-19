package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/03/2023.
 * #2062 https://leetcode.com/problems/count-vowel-substrings-of-a-string/
 */
public class CountVowelSubstringsOfAString {

	// time O(n), space O(1)
	public int countVowelSubstrings(String word) {
		int res = 0, vowelCount = 0;
		int[] map = new int[26];
		for (int i = 0, j = 0, k = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				vowelCount += (++map[c - 'a'] == 1) ? 1 : 0;
				while (vowelCount == 5) {
					vowelCount -= (--map[word.charAt(k++) - 'a'] == 0) ? 1 : 0;
				}
				res += k - j;
			} else {
				Arrays.fill(map, 0);
				vowelCount = 0;
				j = i + 1;
				k = i + 1;
			}
		}
		return res;
	}
}
