package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LongestCommonPrefix;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/13/2016.
 */
public class LongestCommonPrefixTest {
	@Test
	public void validInputShouldReturnLongestCommonPrefix() {
		String[] strs = new String[]{"a"};
		assertEquals(LongestCommonPrefix.longestCommonPrefix(strs), "a");
	}

	@Test
	public void diffInputShouldReturnEmpty() {
		String[] strs = new String[]{"a", "b"};
		assertEquals(LongestCommonPrefix.longestCommonPrefix(strs), "");
	}
}
