package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MaxSumOfRectangleNoLargerThanK;

public class MaxSumOfRectangleNoLargerThanKTest {
	@Test
	public void testcase() {
		MaxSumOfRectangleNoLargerThanK ms = new MaxSumOfRectangleNoLargerThanK();
		int[][] matrix = new int[3][4];
		matrix[0] = new int[]{5, -4, -3, 4};
		matrix[1] = new int[]{-3, -4, 4, 5};
		matrix[2] = new int[]{5, 1, 5, -4};
		int max = ms.maxSumSubmatrix(matrix, 10);
	}
}
