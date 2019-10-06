package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.dynamicProgramming.LongestCommonSubsequence;

import java.util.List;

public class LongestCommonSubsequenceTest {
	@Test
	public void testcase(){
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		String oneLCS = lcs.getLCS("ABCBDAB", "BDCABA");
		List<String> allLCS = lcs.getAllLCS("ABCBDAB", "BDCABA");
	}
}
