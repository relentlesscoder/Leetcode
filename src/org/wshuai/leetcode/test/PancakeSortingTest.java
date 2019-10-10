package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.PancakeSorting;

import java.util.List;

public class PancakeSortingTest {
	@Test
	public void testcase(){
		PancakeSorting ps = new PancakeSorting();
		List<Integer> res = ps.pancakeSort(new int[]{1, 2, 3, 4});
	}

}
