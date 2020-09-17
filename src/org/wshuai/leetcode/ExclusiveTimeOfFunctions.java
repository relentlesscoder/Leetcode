package org.wshuai.leetcode;

import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 03/20/2020.
 * #0636 https://leetcode.com/problems/exclusive-time-of-functions/
 */
public class ExclusiveTimeOfFunctions {

	// time O(n), space O(n)
	public int[] exclusiveTime(int n, List<String> logs) {
		int[] res = new int[n];
		Stack<int[]> stack = new Stack<>();
		for (String log : logs) {
			String[] strs = log.split(":");
			int id = Integer.parseInt(strs[0]), ts = Integer.parseInt(strs[2]);
			if (strs[1].equals("start")) {
				// [Id, timestamp, parentId]
				stack.push(new int[]{id, ts, stack.isEmpty() ? -1 : stack.peek()[0]});
			} else {
				int[] start = stack.pop();
				int cur = ts - start[1] + 1;
				res[start[0]] += cur;
				if (start[2] != -1) {
					res[start[2]] -= cur;
				}
			}
		}
		return res;
	}
}
