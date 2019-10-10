package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/18/2019.
 * #676 https://leetcode.com/problems/implement-magic-dictionary/
 */
public class ImplementMagicDictionary {
	private Map<Integer, Set<String>> map;

	/**
	 * Initialize your data structure here.
	 */
	public ImplementMagicDictionary() {
		map = new HashMap<>();
	}

	/**
	 * Build a dictionary through a list of words
	 */
	public void buildDict(String[] dict) {
		for (String word : dict) {
			int len = word.length();
			if (!map.containsKey(len)) {
				map.put(len, new HashSet<>());
			}
			List<String> vals = getAllOptions(word);
			map.get(len).addAll(vals);
		}
	}

	/**
	 * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
	 */
	public boolean search(String word) {
		int len = word.length();
		Set<String> set = map.get(len);
		if (set == null) {
			return false;
		}
		return set.contains(word);
	}

	private List<String> getAllOptions(String word) {
		List<String> res = new ArrayList<>();
		char[] arr = word.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			char v = arr[i];
			for (int j = 0; j < 26; j++) {
				char c = (char) ('a' + j);
				if (c != v) {
					arr[i] = c;
					res.add(new String(arr));
				}
			}
			arr[i] = v;
		}
		return res;
	}
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */