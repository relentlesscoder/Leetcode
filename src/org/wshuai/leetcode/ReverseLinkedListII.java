package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0092 https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListII {

	// time O(n)
	public LinkedListNode reverseBetween(LinkedListNode head, int m, int n) {
		int i = 0;
		LinkedListNode root = new LinkedListNode(0), cur = head, front = root;
		root.next = head;
		while(cur != null && ++i < m){
			front = cur;
			cur = cur.next;
		}
		LinkedListNode start = cur, prev = null;
		while(cur != null && i++ <= n){
			LinkedListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		front.next = prev;
		start.next = cur;
		return root.next;
	}
}
