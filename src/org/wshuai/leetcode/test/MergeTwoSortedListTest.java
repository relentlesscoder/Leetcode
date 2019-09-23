package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LinkedListNode;
import org.wshuai.leetcode.MergeTwoSortedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/16/2016.
 */
public class MergeTwoSortedListTest {
	@Test
	public void validInputsShouldBeMerged() {
		LinkedListNode l1 = new LinkedListNode(2);
		LinkedListNode l2 = new LinkedListNode(1);
		MergeTwoSortedList mt = new MergeTwoSortedList();
		LinkedListNode r = mt.mergeTwoLists(l1, l2);
	}
}
