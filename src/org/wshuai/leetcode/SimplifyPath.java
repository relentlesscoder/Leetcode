package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/25/16.
 * #71 https://leetcode.com/problems/simplify-path/
 */
public class SimplifyPath {
	public String simplifyPath(String path) {
		StringBuilder sb = new StringBuilder();
		if (path == null || path.isEmpty()) {
			return "";
		}
		Stack<String> stack = new Stack<String>();
		int i = 0;
		int len = path.length();

		if (path.charAt(i) != '/') {
			return "";
		}
		while (i < len && i >= 0) {
			if (i == len - 1) {
				break;
			}
			int j = path.indexOf("/", i + 1);
			String val;
			if (j == -1) {
				val = path.substring(i);
			} else {
				val = path.substring(i, j);
			}
			if (val.equals("/..")) {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else if (val.equals("/.") || val.equals("/")) {
			} else {
				stack.push(val);
			}
			i = j;
		}

		while (!stack.isEmpty()) {
			sb.insert(0, stack.pop());
		}
		String res = sb.toString();
		return res.isEmpty() ? "/" : res;
	}
}
