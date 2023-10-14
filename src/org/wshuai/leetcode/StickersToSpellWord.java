package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/02/2019.
 * #0691 https://leetcode.com/problems/stickers-to-spell-word/
 */
public class StickersToSpellWord {

	// time O((2^m) * n * 26), space O(2^m)
	public int minStickersDP(String[] stickers, String target) {
		int n = stickers.length;
		int[][] stickerMap = new int[n][26];
		Map<String, Integer> dp = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (char c : stickers[i].toCharArray()) {
				stickerMap[i][c - 'a']++;
			}
		}
		dp.put("", 0);
		return dfs(target, stickerMap, dp);
	}

	private int dfs(String target, int[][] stickerMap, Map<String, Integer> dp) {
		if (dp.containsKey(target)) {
			return dp.get(target);
		}
		int count = Integer.MAX_VALUE;
		int[] targetMap = new int[26];
		for (char c : target.toCharArray()) {
			targetMap[c - 'a']++;
		}
		for (int i = 0; i < stickerMap.length; i++) {
			if (stickerMap[i][target.charAt(0) - 'a'] == 0) {
				continue;
			}
			StringBuilder newTarget = new StringBuilder();
			for (int j = 0; j < 26; j++) {
				char c = (char) (j + 'a');
				if (targetMap[j] > 0) {
					for (int k = 0; k < Math.max(0, targetMap[j] - stickerMap[i][j]); k++) {
						newTarget.append(c);
					}
				}
			}
			String next = newTarget.toString();
			int val = dfs(next, stickerMap, dp);
			if (val != -1) {
				count = Math.min(count, 1 + val);
			}
		}
		dp.put(target, count == Integer.MAX_VALUE ? -1 : count);
		return dp.get(target);
	}
}
