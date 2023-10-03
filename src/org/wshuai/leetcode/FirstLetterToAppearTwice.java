package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2351 https://leetcode.com/problems/first-letter-to-appear-twice/
 */
public class FirstLetterToAppearTwice {

	// time O(n), space O(1)
	public char repeatedCharacter(String s) {
		int[] count = new int[26];
		for (char c : s.toCharArray()) {
			if (count[c - 'a'] == 1) {
				return c;
			}
			count[c - 'a']++;
		}
		return '$';
	}
}
