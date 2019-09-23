package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.LinkedListNode;
import org.wshuai.leetcode.RemoveDuplicatesFromSortedListII;

/**
 * Created by Wei on 10/10/16.
 */
public class RemoveDuplicatesFromSortedListIITest {
	@Test
	public void testcase1() {
		RemoveDuplicatesFromSortedListII rd = new RemoveDuplicatesFromSortedListII();
		LinkedListNode root = new LinkedListNode(1);
		root.next = new LinkedListNode(2);
		root.next.next = new LinkedListNode(2);
		LinkedListNode ln = rd.deleteDuplicates(root);
	}

	@Test
	public void testcase2() {
		RemoveDuplicatesFromSortedListII rd = new RemoveDuplicatesFromSortedListII();
		LinkedListNode root = new LinkedListNode(1);
		root.next = new LinkedListNode(2);
		root.next.next = new LinkedListNode(2);
		root.next.next.next = new LinkedListNode(3);
		root.next.next.next.next = new LinkedListNode(4);
		LinkedListNode ln = rd.deleteDuplicates(root);
	}
}
