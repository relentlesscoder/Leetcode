package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LinkedListNode;
import org.wshuai.leetcode.SortList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wei on 8/20/16.
 */
public class SortListTest {
	@Test
	public void testcase() {
		LinkedListNode node1 = new LinkedListNode(3);
		LinkedListNode node2 = new LinkedListNode(2);
		LinkedListNode node3 = new LinkedListNode(4);
		node1.next = node2;
		node2.next = node3;
		LinkedListNode node = SortList.sortList(node1);
	}
}
