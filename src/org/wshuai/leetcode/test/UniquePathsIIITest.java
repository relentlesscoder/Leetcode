package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.UniquePathsIII;

public class UniquePathsIIITest {
	@Test
	public void testcase() {
		UniquePathsIII up = new UniquePathsIII();
		int[][] grid = new int[3][4];
		grid[0] = new int[]{1, 0, 0, 0};
		grid[1] = new int[]{0, 0, 0, 0};
		grid[2] = new int[]{0, 0, 2, -1};
		int x = up.uniquePathsIII(grid);
	}
}
