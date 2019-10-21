package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.GrumpyBookstoreOwner;

public class GrumpyBookstoreOwnerTest {
	@Test
	public void testcase(){
		GrumpyBookstoreOwner gbo = new GrumpyBookstoreOwner();
		int max = gbo.maxSatisfied(new int[]{1,0,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3);
	}
}
