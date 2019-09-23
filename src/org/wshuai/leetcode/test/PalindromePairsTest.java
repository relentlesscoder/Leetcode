package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PalindromePairs;

public class PalindromePairsTest {
	@Test
	public void testcase1() {
		PalindromePairs pp = new PalindromePairs();
		String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
		pp.palindromePairs(words);
	}

	@Test
	public void testcase2() {
		PalindromePairs pp = new PalindromePairs();
		String[] words = {"a", ""};
		pp.palindromePairs(words);
	}
}
