package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.Triangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/8/16.
 */
public class TriangleTest {
	@Test
	public void testcase() {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		List<Integer> fst = new ArrayList<Integer>();
		fst.add(-1);
		lst.add(fst);
		List<Integer> sec = new ArrayList<Integer>();
		sec.add(-2);
		sec.add(-3);
		lst.add(sec);
		int min = Triangle.minimumTotal(lst);
	}
}
