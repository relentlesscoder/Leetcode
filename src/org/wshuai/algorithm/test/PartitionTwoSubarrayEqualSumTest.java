package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.PartitionTwoSubarrayEqualSum;

public class PartitionTwoSubarrayEqualSumTest {
	@Test
	public void testcase(){
		PartitionTwoSubarrayEqualSum pts = new PartitionTwoSubarrayEqualSum();
		boolean can = pts.partitionEqualSum(new int[]{3, 1, 1, 2, 2, 1});
	}
}
