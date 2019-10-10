package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.NumberOfCornerRectangles;

public class NumberOfCornerRectanglesTest {
	@Test
	public void testcase() {
		NumberOfCornerRectangles nc = new NumberOfCornerRectangles();
		int[][] grid = new int[4][3];
		grid[0] = new int[]{0, 1, 0};
		grid[0] = new int[]{1, 0, 1};
		grid[0] = new int[]{1, 0, 1};
		grid[0] = new int[]{0, 1, 0};
		int c = nc.countCornerRectangles(grid);
	}
}
