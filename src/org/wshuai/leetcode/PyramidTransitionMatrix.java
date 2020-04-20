package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/24/2019.
 * #0756 https://leetcode.com/problems/pyramid-transition-matrix/
 */
public class PyramidTransitionMatrix {
	// time O(k^n), k is the size of allowed
	public boolean pyramidTransition(String bottom, List<String> allowed) {
		Map<String, Set<Character>> map = new HashMap<>();
		for (String s : allowed) {
			String key = s.substring(0, 2);
			map.putIfAbsent(key, new HashSet<>());
			map.get(key).add(s.charAt(2));
		}
		return dfs(0, bottom, "", map);
	}

	private boolean dfs(int start, String cur, String next, Map<String, Set<Character>> map) {
		if (cur.length() == 2) {
			return map.containsKey(cur);
		}
		if (start == cur.length() - 1) {
			return dfs(0, next, "", map);
		}
		String key = cur.substring(start, start + 2);
		if (!map.containsKey(key)) {
			return false;
		}
		for (char c : map.get(key)) {
			if (dfs(start + 1, cur, next + c, map)) {
				return true;
			}
		}
		return false;
	}
}
