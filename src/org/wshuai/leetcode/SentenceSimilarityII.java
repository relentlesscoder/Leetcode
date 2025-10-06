package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/14/2019.
 * #0737 https://leetcode.com/problems/sentence-similarity-ii/
 */
public class SentenceSimilarityII {

	public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
		if (words1.length != words2.length) {
			return false;
		}
		Map<String, String> root = new HashMap<>();
		for (List<String> p : pairs) {
			String s1 = p.get(0), s2 = p.get(1);
			String r1 = findRoot(s1, root);
			String r2 = findRoot(s2, root);
			if (!r1.equals(r2)) {
				root.put(r2, r1);
			}
		}
		for (int i = 0; i < words1.length; i++) {
			if (words1[i].equals(words2[i])) {
				continue;
			}
			if (!findRoot(words1[i], root).equals(findRoot(words2[i], root))) {
				return false;
			}
		}
		return true;
	}

	private String findRoot(String s, Map<String, String> root) {
		root.putIfAbsent(s, s);
		if (!s.equals(root.get(s))) {
			String r = findRoot(root.get(s), root);
			root.put(s, r);
		}
		return root.get(s);
	}
}
