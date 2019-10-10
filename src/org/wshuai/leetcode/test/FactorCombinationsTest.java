package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.FactorCombinations;

import java.util.List;

/**
 * Created by Wei on 10/1/2016.
 */
public class FactorCombinationsTest {
	@Test
	public void testcase() {
		FactorCombinations fc = new FactorCombinations();
		List<List<Integer>> ls = fc.getFactors(12);
	}
}
