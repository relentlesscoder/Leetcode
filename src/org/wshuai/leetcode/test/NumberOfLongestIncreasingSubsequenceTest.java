package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.NumberOfLongestIncreasingSubsequence;

public class NumberOfLongestIncreasingSubsequenceTest {
	@Test
	public void testcase(){
		NumberOfLongestIncreasingSubsequence nlis = new NumberOfLongestIncreasingSubsequence();
		int count = nlis.findNumberOfLIS(new int[]{1, 3, 5, 4, 7});
	}
}
