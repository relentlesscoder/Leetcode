package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.RectangleOverlap;

public class RectangleOverlapTest {
	@Test
	public void testcase() {
		RectangleOverlap ro = new RectangleOverlap();
		boolean ov = ro.isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3});
	}
}
