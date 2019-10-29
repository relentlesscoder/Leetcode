package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.OnlineElection;

public class OnlineElectionTest {
	@Test
	public void testcase(){
		OnlineElection oe = new OnlineElection(new int[]{0,1,1,0,0,1,0}, new int[]{0,5,10,15,20,25,30});
		int i1 = oe.q(3);
		int i2 = oe.q(12);
		int i3 = oe.q(25);
		int i4 = oe.q(15);
		int i5 = oe.q(24);
		int i6 = oe.q(8);
	}
}
