package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ValidSquare;

public class ValidSquareTest {
	@Test
	public void testcase(){
		ValidSquare vs = new ValidSquare();
		boolean ivs = vs.validSquare(new int[]{1,1}, new int[]{5,3}, new int[]{3,5}, new int[]{7,7});
	}
}
