package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.LongestPathInMatrix;

public class LongestPathInMatrixTest {
	@Test
	public void testcase(){
		LongestPathInMatrix lpm = new LongestPathInMatrix();
		int[][] M =
			{
				{ 12, 13, 14, 21, 23 },
				{ 13, 9, 22, 2, 3 },
				{ 14, 8, 1, 5, 4 },
				{ 15, 24, 7, 6, 20 },
				{ 16, 17, 18, 19, 25 }
			};
		int res = lpm.longestPath(M);
	}
}
