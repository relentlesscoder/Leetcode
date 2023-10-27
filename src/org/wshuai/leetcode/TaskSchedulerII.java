package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/26/2023.
 * #2365 https://leetcode.com/problems/task-scheduler-ii/
 */
public class TaskSchedulerII {

	// time O(n), space O(n)
	public long taskSchedulerII(int[] tasks, int space) {
		long res = 0;
		Map<Integer, Long> lastTaken = new HashMap<>();
		for (int t : tasks) {
			if (lastTaken.containsKey(t)) {
				res = Math.max(res, lastTaken.get(t) + space) + 1;
			} else {
				res += 1;
			}
			lastTaken.put(t, res);
		}
		return res;
	}
}
