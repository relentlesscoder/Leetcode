package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PacificAtlanticWaterFlow;

/**
 * Created by Wei on 10/29/2016.
 */
public class PacificAtlanticWaterFlowTest {
	@Test
	public void testcase() {
		int[][] matrix = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
		PacificAtlanticWaterFlow pa = new PacificAtlanticWaterFlow();
		pa.pacificAtlantic(matrix);
	}
}
