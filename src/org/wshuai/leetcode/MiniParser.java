package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 3/10/17.
 * #385 https://leetcode.com/problems/mini-parser/
 */
public class MiniParser {
	public NestedInteger deserialize(String s) {
		if (s == null || s.isEmpty()) {
			return null;
		}
		if (s.charAt(0) != '[') {
			return new NestedInteger(Integer.parseInt(s));
		}
		Stack<NestedInteger> stack = new Stack<NestedInteger>();
		int len = s.length();
		int i = 0;
		while (i < len) {
			char c = s.charAt(i);
			if (c == '[') {
				NestedInteger ni = new NestedInteger();
				stack.push(ni);
			} else if (c == ']') {
				if (stack.size() > 1) {
					NestedInteger ni = stack.pop();
					stack.peek().add(ni);
				}
			} else if (c != ',') {
				int idx = (c == '-' ? i + 1 : i);
				while (s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
					idx++;
				}
				String val = s.substring(i, idx);
				if (!val.isEmpty()) {
					NestedInteger ni = stack.peek();
					ni.add(new NestedInteger(Integer.parseInt(val)));
				}
				i = idx - 1;
			}
			i++;
		}
		return stack.peek();
	}
}
