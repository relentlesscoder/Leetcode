package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0328 https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {
	// time O(n), space O(1)
	public LinkedListNode oddEvenList(LinkedListNode head) {
		if(head == null){
			return head;
		}
		LinkedListNode cur = head, odd = new LinkedListNode(0),
				even = new LinkedListNode(0), oddRoot = odd, evenRoot = even;
		while(cur != null){
			LinkedListNode next = cur.next;
			cur.next = null;
			odd.next = cur;
			odd = odd.next;
			if(next == null){
				break;
			}
			cur = next.next;
			next.next = null;
			even.next = next;
			even = even.next;
		}
		odd.next = evenRoot.next;
		return oddRoot.next;
	}
}
