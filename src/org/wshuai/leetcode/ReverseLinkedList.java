package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #206 https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
	public LinkedListNode reverseList(LinkedListNode head) {
		if (head == null) {
			return null;
		}

		LinkedListNode prev = null;
		LinkedListNode curr = head;
		while (curr != null) {
			LinkedListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}
}
