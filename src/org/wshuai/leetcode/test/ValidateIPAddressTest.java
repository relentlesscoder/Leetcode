package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ValidateIPAddress;

public class ValidateIPAddressTest {
	@Test
	public void testcase(){
		ValidateIPAddress vip = new ValidateIPAddress();
		String o = vip.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:");
	}
}
