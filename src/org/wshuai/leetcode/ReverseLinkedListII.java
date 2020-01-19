package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0092 https://leetcode.com/problems/reverse-linked-list-ii/
 */
public class ReverseLinkedListII {
	// time O(n)
	public LinkedListNode reverseBetween(LinkedListNode head, int m, int n) {
		int i = 0;
		LinkedListNode root = new LinkedListNode(0), cur = head, prev = root, start = null;
		root.next = head;
		while(cur != null && ++i < m){
			prev = cur;
			cur = cur.next;
		}
		start = cur;
		LinkedListNode next = null;
		while(cur != null && i++ <= n){
			LinkedListNode temp = cur.next;
			cur.next = next;
			next = cur;
			cur = temp;
		}
		prev.next = next;
		start.next = cur;
		return root.next;
	}
}
