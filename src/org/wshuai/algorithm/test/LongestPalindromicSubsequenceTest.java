package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.LongestPalindromicSubsequence;

public class LongestPalindromicSubsequenceTest {
	@Test
	public void testcase(){
		LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
		int res = lps.longestPalindromic("ABBDCACB");
	}
}
