package org.wshuai.leetcode;

/**
 * Created by Wei on 08/16/2016.
 * #0021 https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedList {

	// time O(m + n), space O(1)
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode root = new ListNode(), prev = root;
		while (list1 != null || list2 != null) {
			if (list1 == null || (list2 != null && list2.val < list1.val)) {
				prev.next = list2;
				prev = list2;
				list2 = list2.next;
			} else {
				prev.next = list1;
				prev = list1;
				list1 = list1.next;
			}
		}
		return root.next;
	}

	// Definition for singly-linked list.
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
