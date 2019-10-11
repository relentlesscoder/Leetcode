package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MinimumAreaRectangle;

public class MinimumAreaRectangleTest {
	@Test
	public void testcase(){
		MinimumAreaRectangle mar = new MinimumAreaRectangle();
		int[][] points = new int[][]{
			{1, 1},
			{1, 3},
			{3, 1},
			{3, 3},
			{2, 2}
		};
		int min = mar.minAreaRect(points);
	}
}


//[[1,1],[1,3],[3,1],[3,3],[2,2]]
