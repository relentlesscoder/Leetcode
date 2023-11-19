package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/29/2023.
 * #2423 https://leetcode.com/problems/remove-letter-to-equalize-frequency/
 */
public class RemoveLetterToEqualizeFrequency {

	// time O(n), space O(n)
	public boolean equalFrequency(String word) {
		int[] freq = new int[26];
		for (char c : word.toCharArray()) {
			freq[c - 'a']++;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < 26; i++) {
			if (freq[i] == 0) {
				continue;
			}
			map.put(freq[i], map.getOrDefault(freq[i], 0) + 1);
		}
		if (map.size() == 1 && (map.containsKey(1) || map.entrySet().iterator().next().getValue() == 1)) { // case like "abcdef" and "aaaaaa"
			return true;
		}
		if (map.size() == 2) {
			int max = -1, min = 1_000;
			for (int key : map.keySet()) {
				max = Math.max(max, key);
				min = Math.min(min, key);
			}
			if ((min == 1 && map.get(min) == 1) || (max - min == 1 && map.get(max) == 1)) { // case like "aaabbbcccd" and "aaabbccdd"
				return true;
			}
		}
		return false;
	}
}
