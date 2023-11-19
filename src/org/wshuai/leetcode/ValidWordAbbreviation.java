package org.wshuai.leetcode;

/**
 * Created by Wei on 10/27/2016.
 * #0408 https://leetcode.com/problems/valid-word-abbreviation/
 */
public class ValidWordAbbreviation {

	// time O(n), space O(1)
	public boolean validWordAbbreviation(String word, String abbr) {
		int m = word.length(), n = abbr.length(), i = 0, j = 0, k = 0;
		for (; i < m && j < n; i++, j++, k = j) {
			char c = abbr.charAt(j);
			if (c == '0') {
				return false;
			}
			if (c >= 'a' && c <= 'z') {
				if (c != word.charAt(i)) {
					return false;
				}
			} else {
				while (j + 1 < n && Character.isDigit(abbr.charAt(j + 1))) {
					j++;
				}
				int count = Integer.parseInt(abbr.substring(k, j + 1));
				i += count - 1;
			}
		}
		return i == m && j == n;
	}
}
