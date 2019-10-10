package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.NumberOfBoomerangs;

/**
 * Created by Wei on 11/11/16.
 */
public class NumberOfBoomerangsTest {
	@Test
	public void testcase() {
		NumberOfBoomerangs nb = new NumberOfBoomerangs();
		int[][] p = new int[][]{
				{0, 0}, {1, 0}, {2, 0}
		};
		int cnt = nb.numberOfBoomerangs(p);
	}
}
