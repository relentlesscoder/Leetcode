package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.MinimumCostToReachLastCell;

public class MinimumCostToReachLastCellTest {
	@Test
	public void testcase(){
		MinimumCostToReachLastCell mct = new MinimumCostToReachLastCell();
		int[][] cost =
			{
				{ 4, 7, 8, 6, 4 },
				{ 6, 7, 3, 9, 2 },
				{ 3, 8, 1, 2, 4 },
				{ 7, 1, 7, 3, 7 },
				{ 2, 9, 8, 9, 3 }
			};
		int c = mct.minimumCost(cost);
	}
}
