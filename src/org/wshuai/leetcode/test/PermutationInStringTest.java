package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PermutationInString;

public class PermutationInStringTest {
	@Test
	public void testcase(){
		PermutationInString pis = new PermutationInString();
		boolean v = pis.checkInclusionOptimized("adc", "dcda");
	}
}
