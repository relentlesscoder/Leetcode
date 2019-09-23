package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.ThreeSum;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/14/2016.
 */
public class ThreeSumTest {
	@Test
	public void validInputShouldReturnAllThreeSums() {
		int[] input = new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
		List<List<Integer>> result = ThreeSum.threeSumBrutalForce(input);
	}
}
