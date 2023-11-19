package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/25/2016.
 * #0071 https://leetcode.com/problems/simplify-path/
 */
public class SimplifyPath {

	// time O(n), space O(n)
	public String simplifyPath(String path) {
		Stack<String> stack = new Stack<>();
		String[] dirs = path.split("/");
		for (String dir : dirs) {
			if (dir.isEmpty() || dir.equals(".")) {
				continue;
			} else if (dir.equals("..")) {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else {
				stack.push(dir);
			}
		}
		StringBuilder res = new StringBuilder();
		for (String s : stack) {
			res.append("/" + s);
		}
		return res.length() == 0 ? "/" : res.toString();
	}
}
