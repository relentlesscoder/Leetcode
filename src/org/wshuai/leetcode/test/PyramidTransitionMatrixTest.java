package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PyramidTransitionMatrix;

import java.util.ArrayList;
import java.util.Arrays;

public class PyramidTransitionMatrixTest {
	@Test
	public void testcase(){
		PyramidTransitionMatrix ptm = new PyramidTransitionMatrix();
		boolean b = ptm.pyramidTransition("BCD", Arrays.asList("BCG", "CDE", "GEA", "FFF"));
	}
}
