package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.WordBreak;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/21/16.
 */
public class WordBreakTest {
	@Test
	public void testcase() {
		Set<String> set = new HashSet<String>();
		set.add("aaaa");
		set.add("aaa");
		boolean b = WordBreak.wordBreak("aaaaaaa", set);
	}
}
