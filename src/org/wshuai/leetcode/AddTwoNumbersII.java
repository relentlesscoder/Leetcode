package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 01/07/2017.
 * #0445 https://leetcode.com/problems/add-two-numbers-ii/
 */
public class AddTwoNumbersII {

	// time O(max(m, n)), space O(max(m, n))
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// 反转链表
		ListNode r1 = reverse(l1);
		ListNode r2 = reverse(l2);
		// #0002 两数相加
		ListNode head = add(r1, r2);
		// 反转结果链表
		return reverse(head);
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

	private ListNode add(ListNode l1, ListNode l2) {
		// 两两相加
		ListNode head = new ListNode(0), curr = head;
		int carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			int n1 = l1 == null ? 0 : l1.val;
			int n2 = l2 == null ? 0 : l2.val;
			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
			int sum = n1 + n2 + carry;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
		}
		return head.next;
	}

	// time O(max(m + n)), space O(m + n)
	public ListNode addTwoNumbersQueue(ListNode l1, ListNode l2) {
		// 双队列
		Deque<ListNode> q1 = new ArrayDeque<>();
		Deque<ListNode> q2 = new ArrayDeque<>();
		while (l1 != null) {
			q1.offer(l1);
			l1 = l1.next;
		}
		while (l2 != null) {
			q2.offer(l2);
			l2 = l2.next;
		}
		int carry = 0;
		ListNode head = new ListNode(0);
		while (!q1.isEmpty() || !q2.isEmpty() || carry != 0) {
			int n1 = q1.isEmpty() ? 0 : q1.pollLast().val;
			int n2 = q2.isEmpty() ? 0 : q2.pollLast().val;
			int sum = n1 + n2 + carry;
			carry = sum / 10;
			ListNode next = head.next;
			head.next = new ListNode(sum % 10);
			head.next.next = next;
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
