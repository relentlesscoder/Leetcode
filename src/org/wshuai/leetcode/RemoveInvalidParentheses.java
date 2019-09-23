package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 7/20/2017.
 * #301 https://leetcode.com/problems/remove-invalid-parentheses/
 */
public class RemoveInvalidParentheses {
	//BFS
	public List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<String>();

		if (s == null) {
			return res;
		}

		Set<String> visited = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();

		queue.add(s);
		visited.add(s);

		boolean found = false;

		while (!queue.isEmpty()) {
			s = queue.poll();

			if (isValid(s)) {
				res.add(s);
				found = true;
			}

			if (found) {
				continue;
			}

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) != '(' && s.charAt(i) != ')') {
					continue;
				}

				String t = s.substring(0, i) + s.substring(i + 1);

				if (!visited.contains(t)) {
					queue.add(t);
					visited.add(t);
				}
			}
		}

		return res;
	}

	private boolean isValid(String s) {
		int count = 0;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				count++;
			}
			if (c == ')' && count-- == 0) {
				return false;
			}
		}

		return count == 0;
	}
}
