package org.wshuai.leetcode;

import org.junit.Test;

public class Tester {
	@Test
	public void testcase(){
		OnlineMajorityElementInSubarray ome = new OnlineMajorityElementInSubarray(new int[]{1,1,2,2,1,1});
		int res = ome.query(2, 3, 2);
	}
}
