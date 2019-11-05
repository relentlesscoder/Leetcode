package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.NumberOfSubarraysWithBoundedMaximum;

public class NumberOfSubarraysWithBoundedMaximumTest {
	@Test
	public void testcase(){
		NumberOfSubarraysWithBoundedMaximum nos = new NumberOfSubarraysWithBoundedMaximum();
		int res = nos.numSubarrayBoundedMax(new int[]{73,55,36,5,55,14,9,7,72,52}, 32, 69);
	}
}
