package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.DesignLinkedList;

public class DesignLinkedListTest {
	@Test
	public void testcase() {
		DesignLinkedList linkedList = new DesignLinkedList();
		linkedList.addAtHead(1);
		linkedList.addAtTail(3);
		linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
		linkedList.get(1);            // returns 2
		linkedList.deleteAtIndex(1);  // now the linked list is 1->3
		linkedList.get(1);            // returns 3
	}
}
