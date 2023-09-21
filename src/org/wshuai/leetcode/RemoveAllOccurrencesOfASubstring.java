package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2023.
 * #1910 https://leetcode.com/problems/remove-all-occurrences-of-a-substring/
 */
public class RemoveAllOccurrencesOfASubstring {

	// time O(n * m), space O(n)
	public String removeOccurrences(String s, String part) {
		return dfs(s, part, part.length());
	}

	private String dfs(String s, String part, int n) {
		for (int i = 0; i < s.length(); i++) {
			if (i + n > s.length()) {
				break;
			}
			if (part.equals(s.substring(i, i + n))) {
				return dfs(s.substring(0, i) + s.substring(i + n), part, n);
			}
		}
		return s;
	}
}
