package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/2023.
 * #1957 https://leetcode.com/problems/delete-characters-to-make-fancy-string/
 */
public class DeleteCharactersToMakeFancyString {

	// time O(n), space O(1)
	public String makeFancyString(String s) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (i >= 2 && s.charAt(i - 1) == c && s.charAt(i - 2) == c) {
				continue;
			}
			res.append(c);
		}
		return res.toString();
	}
}
