package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.SmallestStringWithSwaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallestStringWithSwapsTest {
	@Test
	public void testcase(){
		SmallestStringWithSwaps ssw = new SmallestStringWithSwaps();
		List<Integer> l1 = Arrays.asList(0, 3);
		List<Integer> l2 = Arrays.asList(1, 2);
		List<Integer> l3 = Arrays.asList(0, 2);
		List<List<Integer>> lst = new ArrayList<>();
		lst.add(l1);
		lst.add(l2);
		lst.add(l3);
		String x = ssw.smallestStringWithSwaps("dcab", lst);
	}
}
