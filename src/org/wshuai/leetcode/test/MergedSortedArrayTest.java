package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.MergedSortedArray;

/**
 * Created by Wei on 8/26/16.
 */
public class MergedSortedArrayTest {
	@Test
	public void testcase1() {
		int[] nums1 = {1, 0};
		int m = 1;
		int[] nums2 = {2};
		int n = 1;
		MergedSortedArray.merge(nums1, m, nums2, n);
	}
}
