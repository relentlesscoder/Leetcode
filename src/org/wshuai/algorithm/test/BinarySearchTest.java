package org.wshuai.algorithm.test;

import org.junit.Test;
import org.wshuai.algorithm.binarySearch.BinarySearch;

public class BinarySearchTest {
	@Test
	public void testcase(){
		BinarySearch bs = new BinarySearch();
		int idx = bs.binarySearch(new int[]{1, 4, 5, 9, 12}, 8);
	}
}
