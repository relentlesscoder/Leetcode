package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.Flatten2DVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/6/16.
 */
public class Flatten2DVectorTest {
	@Test
	public void testcase() {
		List<List<Integer>> lst = new ArrayList<List<Integer>>();
		List<Integer> l = new ArrayList<Integer>();
		lst.add(l);
		Flatten2DVector fd = new Flatten2DVector(lst);
		while (fd.hasNext()) {
			fd.next();
		}
	}
}
