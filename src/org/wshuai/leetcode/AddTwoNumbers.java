package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2015.
 * #0002 https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

	// time O(m + n), space O(m + n)
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
