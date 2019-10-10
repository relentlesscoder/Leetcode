package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.FillingBookcaseShelves;

public class FillingBookcaseShelvesTest {
	@Test
	public void testcase(){
		FillingBookcaseShelves fbs = new FillingBookcaseShelves();
		int[][] input = new int[7][2];
		input[0] = new int[]{1, 1};
		input[1] = new int[]{2, 3};
		input[2] = new int[]{2, 3};
		input[3] = new int[]{1, 1};
		input[4] = new int[]{1, 1};
		input[5] = new int[]{1, 1};
		input[6] = new int[]{1, 2};

		int min = fbs.minHeightShelves(input, 4);
	}
}
