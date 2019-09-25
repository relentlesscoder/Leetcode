package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PartitionEqualSubsetSum;

public class PartitionEqualSubsetSumTest {
	@Test
	public void testcase(){
		PartitionEqualSubsetSum pes = new PartitionEqualSubsetSum();
		boolean can = pes.canPartitionDP(new int[]{1, 5, 11, 5});
	}
}
