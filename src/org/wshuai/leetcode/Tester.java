package org.wshuai.leetcode;

import org.junit.Test;

import java.util.List;

public class Tester {
	@Test
	public void testcase(){
		FallingSquares fs = new FallingSquares();
		List<Integer> res = fs.fallingSquares(new int[][]{
				{1,2},{1,3}
		});
	}
}
