package org.wshuai.leetcode;

/**
 * Created by Wei on 10/09/2016.
 * #0061 https://leetcode.com/problems/rotate-list/
 */
public class RotateList {
	public LinkedListNode rotateRight(LinkedListNode head, int k) {
		if(head == null){
			return null;
		}
		// find the tail
		int n = 1;
		LinkedListNode tail = head;
		while(tail.next != null){
			tail = tail.next;
			n++;
		}
		// connect tail to head and advance n - k steps from tail
		tail.next = head;
		if(k % n > 0){
			int i = n - k % n;
			while(i > 0){
				tail = tail.next;
				i--;
			}
		}
		// split the linked list and return the new head
		LinkedListNode res = tail.next;
		tail.next = null;
		return res;
	}
}
