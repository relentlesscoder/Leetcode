package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/20/2023.
 * #2390 https://leetcode.com/problems/removing-stars-from-a-string/
 */
public class RemovingStarsFromAString {

	// time O(n), space O(n)
	public String removeStars(String s) {
		LinkedList<Character> stack = new LinkedList();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '*') {
				stack.pollLast();
			} else {
				stack.offerLast(c);
			}
		}
		StringBuilder res = new StringBuilder();
		while (!stack.isEmpty()) {
			res.append(stack.pollFirst());
		}
		return res.toString();
	}
}
