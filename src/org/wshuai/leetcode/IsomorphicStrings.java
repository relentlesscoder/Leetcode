package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 10/12/2016.
 * #205 https://leetcode.com/problems/isomorphic-strings/
 */
public class IsomorphicStrings {
	public boolean isIsomorphic(String s, String t) {
		if (s == null && t == null) {
			return true;
		}
		if (s == null) {
			return false;
		}
		if (t == null) {
			return false;
		}
		int sLen = s.length();
		int tLen = t.length();
		if (sLen != tLen) {
			return false;
		}

		Map<Character, Character> map = new HashMap<Character, Character>();
		Set<Character> used = new HashSet<Character>();
		for (int i = 0; i < sLen; i++) {
			char x = s.charAt(i);
			char y = t.charAt(i);
			if (map.containsKey(x)) {
				char val = map.get(x);
				if (val != y) {
					return false;
				}
			} else if (used.contains(y)) {
				return false;
			} else {
				map.put(x, y);
				used.add(y);
			}
		}

		return true;
	}
}
