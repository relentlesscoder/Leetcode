package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SubarrayProductLessThanK;

public class SubarrayProductLessThanKTest {
	@Test
	public void testcase() {
		SubarrayProductLessThanK sp = new SubarrayProductLessThanK();
		int count = sp.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100);
	}
}
