package org.wshuai.leetcode;

/**
 * Created by Wei on 09/24/2016.
 * #0369 https://leetcode.com/problems/plus-one-linked-list/
 */
public class PlusOneLinkedList {

	// time O(n), space O(n)
	public ListNode plusOne(ListNode head) {
		// 反转链表
		ListNode root = reverse(head);
		// #0002 加一
		ListNode res = addOne(root);
		// 反转结果链表
		return reverse(res);
	}

	private ListNode reverse(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	private ListNode addOne(ListNode l1) {
		ListNode head = new ListNode(0), curr = head;
		int carry = 1;
		while (l1 != null || carry != 0) {
			int n1 = l1 == null ? 0 : l1.val;
			l1 = l1 == null ? null : l1.next;
			int sum = n1 + carry;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
		}
		return head.next;
	}

	/**
	 * Definition for singly-linked list.
	 **/
	private static class ListNode {
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
