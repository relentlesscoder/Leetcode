package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/01/2023.
 * #2014 https://leetcode.com/problems/longest-subsequence-repeated-k-times/
 */
public class LongestSubsequenceRepeatedKTimes {

	// time O(n * 2^8), space O(2^8)
	public String longestSubsequenceRepeatedK(String s, int k) {
		String res = "";
		int[] count = new int[26];
		for (char c : s.toCharArray()) {
			count[c - 'a']++;
		}
		Deque<String> queue = new ArrayDeque<>();
		queue.offer("");
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				String curr = queue.poll();
				for (char c = 'a'; c <= 'z'; c++) {
					if (count[c - 'a'] < k) {
						continue;
					}
					String next = curr + c;
					if (isRepeatedSub(s, next, k)) {
						res = next;
						queue.offer(next);
					}
				}
			}
		}
		return res;
	}

	private boolean isRepeatedSub(String s, String sub, int k) {
		for (int i = 0, j = 0; i < s.length(); i++) {
			if (s.charAt(i) == sub.charAt(j)) {
				if (++j == sub.length()) {
					if (--k == 0) {
						return true;
					}
					j = 0;
				}
			}
		}
		return false;
	}
}
