package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/16/19.
 * #1048 https://leetcode.com/problems/longest-string-chain/
 */
public class LongestStringChain {
	private int max;
	private Set<String> set;
	private Map<String, Integer> map;

	public int longestStrChain(String[] words) {
		set = new HashSet<>();
		map = new HashMap<>();
		max = 0;
		for (String w : words) {
			set.add(w);
		}
		Arrays.sort(words, (a, b) -> b.length() - a.length());
		for (int i = 0; i < words.length; i++) {
			int len = getLength(words[i]);
			max = Math.max(len, max);
		}
		return max;
	}

	private int getLength(String val) {
		if (map.containsKey(val)) {
			return map.get(val);
		}
		int len = 1;
		for (int i = 0; i < val.length(); i++) {
			String desc = val.substring(0, i) + val.substring(i + 1);
			if (set.contains(desc)) {
				len = Math.max(getLength(desc) + 1, len);
			}
		}
		map.put(val, len);
		return len;
	}
}
