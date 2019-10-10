package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PerfectRectangle;

public class PerfectRectangleTest {
	@Test
	public void testcase1() {
		PerfectRectangle pr = new PerfectRectangle();
		int[][] points = new int[5][4];
		points[0] = new int[]{1, 1, 3, 3};
		points[1] = new int[]{3, 1, 4, 2};
		points[2] = new int[]{3, 2, 4, 4};
		points[3] = new int[]{1, 3, 2, 4};
		points[4] = new int[]{2, 3, 3, 4};
		boolean b = pr.isRectangleCover(points);
	}

	@Test
	public void testcase2() {
		PerfectRectangle pr = new PerfectRectangle();
		int[][] points = new int[2][4];
		points[0] = new int[]{0, 0, 4, 1};
		points[1] = new int[]{0, 0, 4, 1};
		boolean b = pr.isRectangleCover(points);
	}
}
