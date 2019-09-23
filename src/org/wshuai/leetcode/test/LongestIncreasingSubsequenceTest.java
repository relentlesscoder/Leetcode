package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LongestIncreasingSubsequence;

/**
 * Created by Wei on 11/14/16.
 */
public class LongestIncreasingSubsequenceTest {
	@Test
	public void testcase() {
		LongestIncreasingSubsequence li = new LongestIncreasingSubsequence();
		int x = li.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
	}
}
