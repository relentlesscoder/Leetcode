package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0206 https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {
	// time O(n)
	public LinkedListNode reverseList(LinkedListNode head) {
		LinkedListNode prev = null, cur = head;
		while(cur != null){
			LinkedListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
}
