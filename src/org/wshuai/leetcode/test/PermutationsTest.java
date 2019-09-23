package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.Permutations;

import java.util.List;

/**
 * Created by Wei on 10/11/2016.
 */
public class PermutationsTest {
	@Test
	public void testcase() {
		Permutations p = new Permutations();
		List<List<Integer>> r = p.permuteIterative(new int[]{6, 3, 2, 7, 4, -1});
	}
}
