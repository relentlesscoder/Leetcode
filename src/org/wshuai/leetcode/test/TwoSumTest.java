package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.TwoSum;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/9/15.
 */
public class TwoSumTest {
	@Test
	public void twoSumShouldReturnCorrectIndexes() {
		int[] array1 = {3, 2, 4};
		int target1 = 6;

		TwoSum ts = new TwoSum();
		int[] result1 = ts.twoSum(array1, target1);
		assertEquals(2, result1[0]);
		assertEquals(3, result1[1]);
	}
}
