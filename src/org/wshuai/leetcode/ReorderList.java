package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #0143 https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {
	// time O(n), space O(1)
	public void reorderList(LinkedListNode head) {
		if(head == null){
			return;
		}
		// split the list into two lists
		// for list that has odd number of nodes,
		// the seond list has one less node
		LinkedListNode slow = head, fast = head, prev = null;
		while(fast != null && fast.next != null){
			prev = slow;
			fast = fast.next.next;
			slow = slow.next;
		}
		// handle the odd list
		if(fast != null){
			prev = slow;
			slow = slow.next;
		}
		// split the node
		prev.next = null;

		// reverse the second list
		prev = null;
		LinkedListNode cur = slow;
		while(cur != null){
			LinkedListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}

		// merge the two lists
		LinkedListNode left = head, right = prev;
		while(right != null){
			LinkedListNode next = right.next;
			right.next = left.next;
			left.next = right;
			left = right.next;
			right = next;
		}
	}
}
