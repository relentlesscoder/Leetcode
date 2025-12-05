package org.wshuai.leetcode;

/**
 * Created by Wei on 11/01/2019.
 * #1081 https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 */
public class SmallestSubsequenceOfDistinctCharacters {

	// time O(n), space O(n)
	public String smallestSubsequence(String s) {
		// Same as #0316
		char[] cs = s.toCharArray();
		int[] freq = new int[26];
		boolean[] added = new boolean[26];
		StringBuilder stack = new StringBuilder();
		for (char c : cs) {
			freq[c - 'a']++;
		}
		for (char c : cs) {
			freq[c - 'a']--;
			if (added[c - 'a']) {
				continue;
			}
			while (!stack.isEmpty() && stack.charAt(stack.length() - 1) > c
					&& freq[stack.charAt(stack.length() - 1) - 'a'] > 0) {
				added[stack.charAt(stack.length() - 1) - 'a'] = false;
				stack.deleteCharAt(stack.length() - 1);
			}
			stack.append(c);
			added[c - 'a'] = true;
		}
		return stack.toString();
	}
}
