package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2023.
 * #1974 https://leetcode.com/problems/minimum-time-to-type-word-using-special-typewriter/
 */
public class MinimumTimeToTypeWordUsingSpecialTypewriter {

	// time O(n), space O(1)
	public int minTimeToType(String word) {
		int res = 0, start = 0;
		for (char c : word.toCharArray()) {
			int curr = c - 'a';
			res += Math.min(Math.abs(curr - start), 26 - Math.abs(curr - start)) + 1;
			start = curr;
		}
		return res;
	}
}
