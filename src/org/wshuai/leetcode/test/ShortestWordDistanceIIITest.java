package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ShortestWordDistanceIII;

/**
 * Created by Wei on 10/2/16.
 */
public class ShortestWordDistanceIIITest {
	@Test
	public void testcase() {
		ShortestWordDistanceIII sw = new ShortestWordDistanceIII();
		String[] words = new String[]{"a", "a"};
		int dis = sw.shortestWordDistance(words, "a", "a");
	}
}
