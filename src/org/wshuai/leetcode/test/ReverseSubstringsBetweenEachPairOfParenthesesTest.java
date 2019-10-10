package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ReverseSubstringsBetweenEachPairOfParentheses;

public class ReverseSubstringsBetweenEachPairOfParenthesesTest {
	@Test
	public void testcase(){
		ReverseSubstringsBetweenEachPairOfParentheses rsb = new ReverseSubstringsBetweenEachPairOfParentheses();
		String res = rsb.reverseParentheses("(u(love)i)");
	}
}
