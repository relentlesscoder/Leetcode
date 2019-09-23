package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.AdditiveNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Wei on 3/19/17.
 */
public class AdditiveNumberTest {
	@Test
	public void testcase() {
		AdditiveNumber an = new AdditiveNumber();
		boolean x = an.isAdditiveNumber("8917");
	}

	@Test
	public void testcase1() {
		HashSet<List<Integer>> set = new HashSet<>();
		List<Integer> p = new ArrayList<>();
		p.add(0);
		p.add(0);
		set.add(p);

		List<Integer> x = new ArrayList<>();
		x.add(0);
		x.add(0);

		boolean b = set.contains(x);
	}
}
