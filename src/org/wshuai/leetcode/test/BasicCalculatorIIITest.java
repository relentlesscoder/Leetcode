package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.BasicCalculatorIII;

public class BasicCalculatorIIITest {
	@Test
	public void testcase() {
		BasicCalculatorIII bc = new BasicCalculatorIII();
		int res = bc.calculate("1*2-3/4+5*6-7*8+9/10");
	}
}
