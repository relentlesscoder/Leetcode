package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 12/02/2019.
 * #0691 https://leetcode.com/problems/stickers-to-spell-word/
 */
public class StickersToSpellWord {
	// time O((n*26)^d), space O(n^d)
	// d is the length of target
	public int minStickers(String[] stickers, String target) {
		int count = 0, n = stickers.length;
		int[] targetLetters = new int[26];
		int[][] stickerLetters = new int[n][26];
		for (char c : target.toCharArray()) {
			targetLetters[c - 'a']++;
		}
		for (int i = 0; i < n; i++) {
			for (char c : stickers[i].toCharArray()) {
				stickerLetters[i][c - 'a']++;
			}
		}
		String key = toKey(targetLetters);
		LinkedList<int[]> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.offerLast(targetLetters);
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] cur = queue.pollFirst();
				key = toKey(cur);
				if (!visited.add(key)) {
					continue;
				}
				for (int i = 0; i < n; i++) {
					if (stickerLetters[i][key.charAt(0) - 'a'] == 0) {
						continue;
					}
					int[] temp = cur.clone();
					for (int j = 0; j < 26; j++) {
						if (temp[j] > 0) {
							temp[j] = Math.max(0, temp[j] - stickerLetters[i][j]);
						}
					}
					if (targetSpelled(temp)) {
						return count + 1;
					}
					queue.offerLast(temp);
				}
			}
			count++;
		}
		return -1;
	}

	private boolean targetSpelled(int[] arr) {
		for (int i = 0; i < 26; i++) {
			if (arr[i] > 0) {
				return false;
			}
		}
		return true;
	}

	private String toKey(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (arr[i] == 0) {
				continue;
			}
			char c = (char) (i + 'a');
			for (int j = 0; j < arr[i]; j++) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
