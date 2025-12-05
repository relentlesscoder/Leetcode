package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/30/2019.
 * #0734 https://leetcode.com/problems/sentence-similarity/
 */
public class SentenceSimilarity {

	// time O(n), space O(n)
	public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
		int m = sentence1.length, n = sentence2.length;
		if (m != n) {
			return false;
		}
		Map<String, Set<String>> map = new HashMap<>();
		for (List<String> pair : similarPairs) {
			map.computeIfAbsent(pair.get(0), value -> new HashSet<>()).add(pair.get(1));
			map.computeIfAbsent(pair.get(1), value -> new HashSet<>()).add(pair.get(0));
		}
		for (int i = 0; i < n; i++) {
			if (sentence1[i].equals(sentence2[i])) {
				continue;
			}
			if (map.containsKey(sentence1[i])
					&& map.get(sentence1[i]).contains(sentence2[i])) {
				continue;
			}
			return false;
		}
		return true;
	}
}
