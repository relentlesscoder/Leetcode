package org.wshuai.leetcode;

/**
 * Created by Wei on 09/26/2023.
 * #2095 https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 */
public class DeleteTheMiddleNodeOfALinkedList {

	// time O(n), space O(1)
	public ListNode deleteMiddle(ListNode head) {
		if (head.next == null) {
			return null;
		}
		ListNode fastNode = head.next.next, slowNode = head; // slow node reaches the element before the middle at the end
		while (fastNode != null && fastNode.next != null) {
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
		}
		slowNode.next = slowNode.next.next;
		return head;
	}

	// time O(n), space O(1)
	public ListNode deleteMiddleLargerMiddle(ListNode head) {
		if (head.next == null) { // special case: single node list
			return null;
		}
		ListNode fastNode = head, slowNode = head, prev = null; // slow node reaches ceiling((n - 1) / 2) at the end
		while (fastNode != null && fastNode.next != null) { // use fast and slow node to find the node to delete
			fastNode = fastNode.next.next;
			prev = slowNode;
			slowNode = slowNode.next;
		}
		if (slowNode != null) {
			prev.next = slowNode.next;
		}
		return head;
	}

	/**
	 * Definition for singly-linked list.
	 **/
	private class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}
