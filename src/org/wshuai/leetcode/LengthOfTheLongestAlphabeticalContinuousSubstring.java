package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2023.
 * #2414 https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring/
 */
public class LengthOfTheLongestAlphabeticalContinuousSubstring {

	// time O(n), space O(1)
	public int longestContinuousSubstring(String s) {
		int res = 1, curr = 1;
		for (int i = 1; i < s.length(); i++) {
			int r = s.charAt(i) - 'a', l = s.charAt(i - 1) - 'a';
			if (r - l == 1) {
				res = Math.max(res, ++curr);
			} else {
				curr = 1;
			}
		}
		return res;
	}
}
