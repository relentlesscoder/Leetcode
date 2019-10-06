package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.NumberOfPathsWithGivenCostInMatrix;

public class NumberOfPathsWithGivenCostInMatrixTest {
	@Test
	public void testcase(){
		NumberOfPathsWithGivenCostInMatrix npg = new NumberOfPathsWithGivenCostInMatrix();
		int[][] M =
			{
				{ 4, 7, 1, 6},
				{ 5, 7, 3, 9},
				{ 3, 2, 1, 2},
				{ 7, 1, 6, 3}
			};
		int res = npg.numberOfPaths(M, 25);
	}
}
