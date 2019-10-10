package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.TernaryExpressionParser;

/**
 * Created by Wei on 10/30/16.
 */
public class TernaryExpressionParserTest {
	@Test
	public void testcase() {
		TernaryExpressionParser te = new TernaryExpressionParser();
		String x = te.parseTernary("T?T:F?T?1:2:F?3:4");
	}
}
