package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/20/2023.
 * #2390 https://leetcode.com/problems/removing-stars-from-a-string/
 */
public class RemovingStarsFromAString {

	// time O(n), space O(1)
	public String removeStars(String s) {
		int n = s.length(), count = 0;
		StringBuilder res = new StringBuilder();
		for (int i = n - 1; i >= 0; i--) {
			char cur = s.charAt(i);
			if (cur == '*' || count > 0) {
				count += cur == '*' ? 1 : -1;
			} else {
				res.append(cur);
			}
		}
		return res.reverse().toString();
	}

	// time O(n), space O(n)
	public String removeStarsStack(String s) {
		StringBuilder res = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<>();
		for (char c : s.toCharArray()) {
			if (c == '*') {
				stack.pop();
			} else {
				stack.push(c);
			}
		}
		while (!stack.isEmpty()) {
			res.append(stack.pollLast());
		}
		return res.toString();
	}
}
