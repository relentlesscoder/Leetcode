package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/26/2023.
 * #2487 https://leetcode.com/problems/remove-nodes-from-linked-list/
 */
public class RemoveNodesFromLinkedList {

	// time O(n), space O(1)
	public ListNode removeNodes(ListNode head) {
		head = reverse(head); // reverse the linked list
		int max = head.val;
		ListNode curr = head, prev = null;
		while (curr != null) {
			if (curr.val < max) { // if there is greater value before the current, remove the current node
				prev.next = curr.next;
			} else { // update the max if greater value (than the current max) is found
				max = Math.max(max, curr.val);
				prev = curr;
			}
			curr = curr.next;
		}
		return reverse(head); // reverse the linked list again
	}

	private ListNode reverse(ListNode head) {
		ListNode curr = head, prev = null;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	// time O(n), space O(n)
	public ListNode removeNodesMonotonicStack(ListNode head) {
		ListNode root = new ListNode(1_000_000);
		root.next = head;
		LinkedList<ListNode> queue = new LinkedList<>();
		ListNode curr = head;
		queue.offerLast(root);
		while (curr != null) {
			while (!queue.isEmpty() && queue.peekLast().val < curr.val) {
				queue.pollLast();
			}
			queue.peekLast().next = curr;
			queue.offerLast(curr);
			curr = curr.next;
		}
		return queue.peekFirst().next;
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
