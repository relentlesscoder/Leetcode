package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/2016.
 * #0083 https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class RemoveDuplicatesFromSortedList {

	// time O(n), space O(1)
	public ListNode deleteDuplicates(ListNode head) {
		// #0203 类似题: 对每一个节点判断下一个节点是否具有相同的值，如果相同则删除当前节点。
		ListNode root = new ListNode(-1000), curr = head, prev = root;
		root.next = head;
		while (curr != null) {
			ListNode next = curr.next;
			if (prev.val == curr.val) {
				prev.next = next;
				curr.next = null;
			} else {
				prev = curr;
			}
			curr = next;
		}
		return root.next;
	}

	// time O(n), space O(MAX)
	public ListNode deleteDuplicatesVisitedMap(ListNode head) {
		boolean[] visited = new boolean[201];
		ListNode root = new ListNode(-1), curr = head, prev = root;
		root.next = head;
		while (curr != null) {
			ListNode next = curr.next;
			if (visited[curr.val + 100]) {
				prev.next = next;
				curr.next = null;
			} else {
				visited[curr.val + 100] = true;
				prev = curr;
			}
			curr = next;
		}
		return root.next;
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
