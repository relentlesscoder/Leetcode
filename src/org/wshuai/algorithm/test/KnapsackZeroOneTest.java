package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.KnapsackZeroOne;

public class KnapsackZeroOneTest {
	@Test
	public void testcase(){
		KnapsackZeroOne kzo = new KnapsackZeroOne();
		int val = kzo.getMaxValue(10, new int[]{20, 5, 10, 40, 15, 25}, new int[]{1, 2, 3, 8, 7, 4});
	}
}
