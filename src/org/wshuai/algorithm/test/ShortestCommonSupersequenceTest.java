package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.ShortestCommonSupersequence;

public class ShortestCommonSupersequenceTest {
	@Test
	public void testcase(){
		ShortestCommonSupersequence scs = new ShortestCommonSupersequence();
		int res = scs.shortestCommonSupersequence("ABCBDAB", "BDCABA");
	}
}
