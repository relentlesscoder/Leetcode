package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ConstructQuadTree;
import org.wshuai.leetcode.QuadTreeNode;

public class ConstructQuadTreeTest {
	@Test
	public void testcase() {
		ConstructQuadTree qt = new ConstructQuadTree();
		int[][] grid = new int[8][8];
		grid[0] = new int[]{1, 1, 1, 1, 0, 0, 0, 0};
		grid[1] = new int[]{1, 1, 1, 1, 0, 0, 0, 0};
		grid[2] = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
		grid[3] = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
		grid[4] = new int[]{1, 1, 1, 1, 0, 0, 0, 0};
		grid[5] = new int[]{1, 1, 1, 1, 0, 0, 0, 0};
		grid[6] = new int[]{1, 1, 1, 1, 0, 0, 0, 0};
		grid[7] = new int[]{1, 1, 1, 1, 0, 0, 0, 0};
		QuadTreeNode root = qt.construct(grid);
	}
}
