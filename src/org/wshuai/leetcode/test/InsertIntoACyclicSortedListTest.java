package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.InsertIntoACyclicSortedList;
import org.wshuai.leetcode.LinkedListNode;

public class InsertIntoACyclicSortedListTest {
	@Test
	public void testcase1() {
		InsertIntoACyclicSortedList ii = new InsertIntoACyclicSortedList();
		LinkedListNode first = new LinkedListNode(3, null);
		LinkedListNode second = new LinkedListNode(4, null);
		LinkedListNode third = new LinkedListNode(1, null);
		first.next = second;
		second.next = third;
		third.next = first;
		ii.insert(first, 2);
	}

	@Test
	public void testcase2() {
		InsertIntoACyclicSortedList ii = new InsertIntoACyclicSortedList();
		LinkedListNode first = new LinkedListNode(3, null);
		LinkedListNode second = new LinkedListNode(3, null);
		LinkedListNode third = new LinkedListNode(3, null);
		first.next = second;
		second.next = third;
		third.next = first;
		ii.insert(first, 0);
	}
}


//{"$id":"1","next":{"$id":"2","next":{"$id":"3","next":{"$ref":"1"},"val":1},"val":4},"val":3}
//        2

//{"$id":"1","next":{"$id":"2","next":{"$id":"3","next":{"$ref":"1"},"val":3},"val":3},"val":3}
//        0