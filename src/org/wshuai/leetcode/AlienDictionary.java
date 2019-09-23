package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 7/4/17.
 * #269 https://leetcode.com/problems/alien-dictionary/
 */
public class AlienDictionary {
	public String alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return "";
		}
		if (words.length == 1) {
			return words[0];
		}
		StringBuilder res = new StringBuilder();
		int len = words.length;
		String prev = words[0];
		Map<Character, Set<Character>> edges = new HashMap<Character, Set<Character>>();
		Set<Character> keys = new HashSet<Character>();
		for (int i = 1; i < len; i++) {
			String curr = words[i];
			int j = 0;
			int k = 0;
			boolean set = false;
			while (j < prev.length() || k < curr.length()) {
				if (j < prev.length() && k < curr.length()) {
					char p = prev.charAt(j);
					char c = curr.charAt(k);
					if (p == c) {
						keys.add(c);
					} else if (!set) {
						edges.putIfAbsent(p, new HashSet<Character>());
						if (!edges.get(p).contains(c)) {
							edges.get(p).add(c);
							keys.add(c);
							keys.add(p);
						}
						set = true;
					} else {
						keys.add(c);
						keys.add(p);
					}
				} else if (j < prev.length()) {
					char p = prev.charAt(j);
					keys.add(p);
				} else {
					char c = curr.charAt(k);
					keys.add(c);
				}
				j++;
				k++;
			}
			prev = curr;
		}

		Stack<Character> stack = new Stack<Character>();
		int[] visited = new int[26];
		for (char key : keys) {
			if (!alienOrderUtil(key, visited, stack, edges)) {
				return "";
			}
		}
		while (!stack.isEmpty()) {
			res.append(stack.pop());
		}
		return res.toString();
	}

	private boolean alienOrderUtil(char key, int[] visited, Stack<Character> stack,
	                               Map<Character, Set<Character>> edges) {
		int idx = key - 'a';
		if (visited[idx] == -1) {
			return false;
		}
		if (visited[idx] == 1) {
			return true;
		}
		visited[idx] = -1;
		if (edges.containsKey(key)) {
			for (char next : edges.get(key)) {
				if (!alienOrderUtil(next, visited, stack, edges)) {
					return false;
				}
			}
		}
		visited[idx] = 1;
		stack.push(key);
		return true;
	}
}
