package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SimplifyPath;

/**
 * Created by Wei on 10/25/16.
 */
public class SimplifyPathTest {
	@Test
	public void testcase() {
		SimplifyPath sp = new SimplifyPath();
		String x = sp.simplifyPath("///");
	}

	@Test
	public void testcase1() {
		SimplifyPath sp = new SimplifyPath();
		String x = sp.simplifyPath("/.");
	}

	@Test
	public void testcase2() {
		SimplifyPath sp = new SimplifyPath();
		String x = sp.simplifyPath("/...");
	}
}
