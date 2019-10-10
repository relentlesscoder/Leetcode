package org.wshuai.leetcode;

/**
 * Created by Wei on 8/19/19.
 * #1160 https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
 */
public class FindWordsThatCanBeFormedByCharacters {
	public int countCharacters(String[] words, String chars) {
		int res = 0;
		int[] map = new int[26];
		for (char c : chars.toCharArray()) {
			map[c - 'a']++;
		}
		for (String s : words) {
			int[] curr = new int[26];
			for (char c : s.toCharArray()) {
				curr[c - 'a']++;
			}
			boolean good = true;
			for (int i = 0; i < 26; i++) {
				if (curr[i] > map[i]) {
					good = false;
					break;
				}
			}
			res += good ? s.length() : 0;
		}
		return res;
	}
}
