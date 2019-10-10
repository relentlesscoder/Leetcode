package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.FindRightInterval;
import org.wshuai.leetcode.Interval;

/**
 * Created by Wei on 11/12/16.
 */
public class FindRightIntervalTest {
	@Test
	public void testcase() {
		FindRightInterval fr = new FindRightInterval();
		Interval[] intervals = new Interval[200];
		for (int i = 0; i < 200; i++) {
			intervals[i] = new Interval(i - 100, i - 98);
		}
		int[] res = fr.findRightInterval(intervals);
	}
}
