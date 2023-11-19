package org.wshuai.leetcode;

/**
 * Created by Wei on 12/05/2020.
 * #1669 https://leetcode.com/problems/merge-in-between-linked-lists/
 */
public class MergeInBetweenLinkedLists {

	// time O(m+n)
	public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
		ListNode root = new ListNode(0), cur1 = list1, cur2 = list2, prev1 = root;
		root.next = list1;
		int i = 0;
		while (i < a) {
			prev1 = cur1;
			cur1 = cur1.next;
			i++;
		}
		prev1.next = list2;
		while (cur2.next != null) {
			cur2 = cur2.next;
		}
		while (i <= b) {
			cur1 = cur1.next;
			i++;
		}
		cur2.next = cur1;
		return root.next;
	}

	// Definition for singly-linked list.
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
