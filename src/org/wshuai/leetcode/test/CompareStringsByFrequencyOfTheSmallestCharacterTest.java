package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.CompareStringsByFrequencyOfTheSmallestCharacter;

public class CompareStringsByFrequencyOfTheSmallestCharacterTest {
	@Test
	public void testcase() {
		CompareStringsByFrequencyOfTheSmallestCharacter cs = new CompareStringsByFrequencyOfTheSmallestCharacter();
		int[] res = cs.numSmallerByFrequency(new String[]{"bbb", "cc"}, new String[]{"a", "aa", "aaa", "aaaa"});
	}
}
