package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.MinimumCoinChangeMaking;

public class MinimumCoinChangeMakingTest {
	@Test
	public void testcase(){
		MinimumCoinChangeMaking mcc = new MinimumCoinChangeMaking();
		int min = mcc.minimumCoinChange(new int[]{5, 3, 9}, 4);
	}
}
