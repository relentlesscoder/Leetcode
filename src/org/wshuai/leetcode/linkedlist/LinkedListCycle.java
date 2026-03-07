package org.wshuai.leetcode.linkedlist;

/**
 * Created by Wei on 10/26/2016.
 * #0141 https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {

	// time O(n), space O(1)
	public boolean hasCycle(ListNode head) {
		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			// 如果快慢指针能相遇则一定有环
			if (fast == slow) {
				return true;
			}
		}
		return false;
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
