package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MaximumSumOfTwoNonOverlappingSubarrays;

public class MaximumSumOfTwoNonOverlappingSubarraysTest {
	@Test
	public void testcase(){
		MaximumSumOfTwoNonOverlappingSubarrays mst = new MaximumSumOfTwoNonOverlappingSubarrays();
		int sum = mst.maxSumTwoNoOverlap(new int[]{3,8,1,3,2,1,8,9,0}, 3, 2);
	}
}