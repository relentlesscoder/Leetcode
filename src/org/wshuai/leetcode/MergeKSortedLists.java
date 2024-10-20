package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 10/11/2016.
 * #0023 https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {

	// time O(n*log(k)), space O(k)
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
		ListNode root = new ListNode(), prev = root;
		for (ListNode node : lists) {
			if (node != null) {
				queue.offer(node);
			}
		}
		while (!queue.isEmpty()) {
			ListNode curr = queue.poll();
			prev.next = curr;
			prev = curr;
			if (curr.next != null) {
				queue.offer(curr.next);
			}
		}
		return root.next;
	}

	// time O(nk*log(k)), space O(1)
	public ListNode mergeKListsDivideAndConquer(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		return merge(lists, 0, lists.length - 1);
	}

	private ListNode merge(ListNode[] lists, int i, int j) {
		if (i == j) {
			return lists[i];
		}
		int k = (i + j) / 2;
		ListNode left = merge(lists, i, k), right = merge(lists, k + 1, j);
		return mergeTwoList(left, right);
	}

	private ListNode mergeTwoList(ListNode node1, ListNode node2) {
		ListNode root = new ListNode(), prev = root;
		while (node1 != null || node2 != null) {
			if (node1 == null || (node2 != null && node2.val < node1.val)) {
				prev.next = node2;
				prev = node2;
				node2 = node2.next;
			} else {
				prev.next = node1;
				prev = node1;
				node1 = node1.next;
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
