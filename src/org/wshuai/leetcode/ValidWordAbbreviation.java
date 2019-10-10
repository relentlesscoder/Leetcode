package org.wshuai.leetcode;

/**
 * Created by Wei on 10/27/2016.
 * #408 https://leetcode.com/problems/valid-word-abbreviation/
 */
public class ValidWordAbbreviation {
	public boolean validWordAbbreviation(String word, String abbr) {
		if (word == null || abbr == null) {
			return false;
		}
		int wLen = word.length();
		int aLen = abbr.length();
		int i = 0;
		int s = 0;
		int j = 0;
		while (i < aLen) {
			char val = abbr.charAt(i);
			if (Character.isDigit(val)) {
				i++;
			} else {
				if (i > s) {
					if (abbr.charAt(s) == '0') {
						return false;
					}
					int num = Integer.parseInt(abbr.substring(s, i));
					j += num;
					s = i;
				}
				if (j < wLen && word.charAt(j) == abbr.charAt(i)) {
					i++;
					s++;
					j++;
				} else {
					return false;
				}
			}
		}
		if (i > s) {
			if (abbr.charAt(s) == '0') {
				return false;
			}
			int num = Integer.parseInt(abbr.substring(s, i));
			j += num;
		}
		return (j == wLen);
	}
}
