package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.CourseSchedule;

/**
 * Created by Wei on 12/4/16.
 */
public class CourseScheduleTest {
	@Test
	public void testcase() {
		CourseSchedule cs = new CourseSchedule();
		int[][] pre = new int[1][2];
		pre[0] = new int[]{1, 0};
		boolean cf = cs.canFinishDFS(2, pre);
	}
}
