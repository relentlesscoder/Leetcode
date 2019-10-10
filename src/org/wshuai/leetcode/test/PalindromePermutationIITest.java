package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PalindromePermutationII;

import java.util.List;

/**
 * Created by Wei on 11/11/16.
 */
public class PalindromePermutationIITest {
	@Test
	public void testcase() {
		PalindromePermutationII pp = new PalindromePermutationII();
		List<String> s = pp.generatePalindromes("aabb");
	}
}
