package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/25/2016.
 * #0071 https://leetcode.com/problems/simplify-path/
 */
public class SimplifyPath {

	// time O(n), space O(n)
	public String simplifyPath(String path) {
		Deque<String> queue = new ArrayDeque<>();
		String[] dirs = path.split("/");
		for (String dir : dirs) {
			if (dir.isEmpty() || dir.equals(".")) {
				continue;
			} else if (dir.equals("..")) {
				queue.pollLast();
			} else {
				queue.offerLast(dir);
			}
		}
		StringBuilder res = new StringBuilder();
		while (!queue.isEmpty()) {
			res.append("/").append(queue.pollFirst());
		}
		return res.isEmpty() ? "/" : res.toString();
	}
}
