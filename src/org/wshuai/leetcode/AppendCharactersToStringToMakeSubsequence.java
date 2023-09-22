package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2023.
 * #2486 https://leetcode.com/problems/append-characters-to-string-to-make-subsequence/
 */
public class AppendCharactersToStringToMakeSubsequence {

	// time O(m + n), space O(1)
	public int appendCharacters(String s, String t) {
		int res = 0, i = 0, j = 0;
		while (i < s.length() && j < t.length()) {
			if (s.charAt(i) == t.charAt(j)) {
				j++;
			}
			i++;
		}
		if (i == s.length()) {
			res += t.length() - j;
		}
		return res;
	}
}
