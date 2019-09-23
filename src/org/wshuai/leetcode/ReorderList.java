package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #143 https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 * int val;
	 * ListNode next;
	 * ListNode(int x) { val = x; }
	 * }
	 */
	public class Solution {
		public void reorderList(LinkedListNode head) {
			if (head == null || head.next == null || head.next.next == null) {
				return;
			}

			// Find the middle
			LinkedListNode fast = head;
			LinkedListNode slow = head;
			while (fast != null) {
				slow = slow.next;
				fast = fast.next;
				if (fast != null) {
					fast = fast.next;
				}
			}

			// Split the list
			LinkedListNode it = head;
			while (it.next != slow) {
				it = it.next;
			}
			it.next = null;

			// Reverse the 2nd half
			LinkedListNode prev = null;
			LinkedListNode curr = slow;
			while (curr != null) {
				LinkedListNode nxt = curr.next;
				curr.next = prev;
				prev = curr;
				curr = nxt;
			}

			// Reorder
			LinkedListNode left = head;
			LinkedListNode right = prev;
			while (right != null) {
				LinkedListNode nxtLeft = left.next;
				LinkedListNode nxtRight = right.next;
				left.next = right;
				right.next = nxtLeft;
				left = nxtLeft;
				right = nxtRight;
			}
		}
	}
}
