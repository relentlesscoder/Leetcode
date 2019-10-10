package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 8/31/2019.
 * #890 https://leetcode.com/problems/find-and-replace-pattern/
 */
public class FindAndReplacePattern {
	public List<String> findAndReplacePattern(String[] words, String pattern) {
		List<String> res = new ArrayList<>();
		for (String word : words) {
			// records the characters in word that already mapped
			int[] used = new int[26];
			boolean match = true;
			Map<Character, Character> map = new HashMap<>();
			char[] wa = word.toCharArray();
			char[] pa = pattern.toCharArray();
			for (int i = 0; i < wa.length; i++) {
				if (map.containsKey(pa[i])) {
					char p = map.get(pa[i]);
					if (p != wa[i]) {
						match = false;
						break;
					}
				} else {
					// different characters in word cannot map to the same character in pattern
					if (used[wa[i] - 'a'] == 1) {
						match = false;
						break;
					}
					map.put(pa[i], wa[i]);
					used[wa[i] - 'a'] = 1;
				}
			}
			if (match) {
				res.add(word);
			}
		}
		return res;
	}
}
