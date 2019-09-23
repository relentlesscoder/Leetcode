package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.TaskScheduler;

public class TaskSchedulerTest {
	@Test
	public void testcase() {
		TaskScheduler ts = new TaskScheduler();
		int t = ts.leastIntervalPriorityQueue(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
	}
}
