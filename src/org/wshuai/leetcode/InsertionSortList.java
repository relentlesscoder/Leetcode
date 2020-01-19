package org.wshuai.leetcode;

/**
 * Created by Wei on 01/18/2020.
 * #0147 https://leetcode.com/problems/insertion-sort-list/
 */
public class InsertionSortList {
	// time O(n^2)
	public LinkedListNode insertionSortList(LinkedListNode head) {
		LinkedListNode root = new LinkedListNode(Integer.MIN_VALUE), cur = root.next, prev = root;
		while(head != null){
			LinkedListNode next = head.next;
			while(cur != null && cur.val < head.val){
				prev = cur;
				cur = cur.next;
			}
			head.next = prev.next;
			prev.next = head;
			head = next;
			cur = root.next;
			prev = root;
		}
		return root.next;
	}
}
