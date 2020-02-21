package org.wshuai.leetcode;

/**
 * Created by Wei on 10/27/2016.
 * #0408 https://leetcode.com/problems/valid-word-abbreviation/
 */
public class ValidWordAbbreviation {
	// time O(n)
	public boolean validWordAbbreviation(String word, String abbr) {
		int i = 0, j = 0, m = word.length(), n = abbr.length();
		StringBuilder sb = new StringBuilder();
		while (j < n) {
			char c = abbr.charAt(j);
			if (c >= '0' && c <= '9') {
				sb.append(c);
				j++;
			} else if (c >= 'a' && c <= 'z') {
				if (sb.length() > 0) {
					if (sb.charAt(0) == '0') {
						return false;
					}
					int count = Integer.parseInt(sb.toString());
					i += count;
					sb = new StringBuilder();
				}
				if (i < m && word.charAt(i) == c) {
					i++;
					j++;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		if (sb.length() > 0) {
			if (sb.charAt(0) == '0') {
				return false;
			}
			int count = Integer.parseInt(sb.toString());
			i += count;
		}
		return i == m;
	}
}
