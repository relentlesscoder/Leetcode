package org.wshuai.leetcode;

/**
 * Created by Wei on 08/24/2019.
 * #0748 https://leetcode.com/problems/shortest-completing-word/
 */
public class ShortestCompletingWord {
	// time O(n)
	public String shortestCompletingWord(String licensePlate, String[] words) {
		String res = "";
		int n = words.length, diff = 'a' - 'A';
		int[] target = new int[26];
		for (char c : licensePlate.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				target[c - 'a']++;
			} else if (c >= 'A' && c <= 'Z') {
				target[c + diff - 'a']++;
			}
		}
		for (int i = 0; i < n; i++) {
			if (!res.isEmpty() && words[i].length() >= res.length()) {
				continue;
			}
			int[] cur = target.clone();
			for (char c : words[i].toCharArray()) {
				if (cur[c - 'a'] > 0) {
					cur[c - 'a']--;
				}
			}
			int sum = 0;
			for (int v : cur) {
				sum += v;
			}
			if (sum == 0) {
				res = words[i];
			}
		}
		return res;
	}
}
