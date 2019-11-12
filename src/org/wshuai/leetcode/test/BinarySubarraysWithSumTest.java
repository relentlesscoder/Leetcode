package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.BinarySubarraysWithSum;

public class BinarySubarraysWithSumTest {
	@Test
	public void testcase(){
		BinarySubarraysWithSum bsw = new BinarySubarraysWithSum();
		int cnt = bsw.numSubarraysWithSum(new int[]{0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0}, 3);
	}
}
