package org.wshuai.leetcode;

/**
 * Created by Wei on 09/02/2016.
 * #0234 https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {

	// time O(n), space O(1)
	public boolean isPalindrome(ListNode head) {
		// 快慢指针找到中间节点
		ListNode middleNode = getMiddle(head);
		// 反转链表的后半部分
		ListNode head2 = reverse(middleNode);
		// 判断是否为回文
		// 图见 https://docs.google.com/document/d/1wv3GpVSg-uL6oOGD63raYhA9q37muDY1gyyUeoSe5Cw
		while (head2 != null) {
			if (head.val != head2.val) {
				return false;
			}
			head = head.next;
			head2 = head2.next;
		}
		return true;
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

	private ListNode getMiddle(ListNode head) {
		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
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
