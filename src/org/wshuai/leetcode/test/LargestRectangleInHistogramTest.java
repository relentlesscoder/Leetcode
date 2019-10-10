package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LargestRectangleInHistogram;

/**
 * Created by Wei on 10/4/16.
 */
public class LargestRectangleInHistogramTest {
	@Test
	public void testcase() {
		LargestRectangleInHistogram lr = new LargestRectangleInHistogram();
		int[] num = new int[]{2, 1, 5, 6, 2, 3};
		int max = lr.largestRectangleArea(num);
	}
}
