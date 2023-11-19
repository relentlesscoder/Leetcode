package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2023.
 * #2309 https://leetcode.com/problems/greatest-english-letter-in-upper-and-lower-case/
 */
public class GreatestEnglishLetterInUpperAndLowerCase {

	// time O(n), space O(1)
	public String greatestLetter(String s) {
		int[] map = new int[52];
		for (char c : s.toCharArray()) {
			if (Character.isUpperCase(c)) {
				map[c - 'A' + 26]++;
			} else {
				map[c - 'a']++;
			}
		}
		for (int i = 25; i >= 0; i--) {
			if (map[i] > 0 && map[i + 26] > 0) {
				return "" + (char)(i + 'A');
			}
		}
		return "";
	}
}
