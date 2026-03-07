package org.wshuai.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/29/2019.
 * #1171 https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 */
public class RemoveZeroSumConsecutiveNodesFromLinkedList {

	// time O(n), space O(n)
	public ListNode removeZeroSumSublists(ListNode head) {
		ListNode root = new ListNode(-1, head);
		// 维护一个前缀和哈希表，键为前缀和值为对应的节点。
		Map<Integer, ListNode> prefix = new HashMap<>();
		prefix.put(0, root);
		for (int sum = 0; head != null; ) {
			ListNode next = head.next;
			sum += head.val;
			// 如果哈希表中已经存在相同的前缀和
			if (prefix.containsKey(sum)) {
				// 则删除中间所有节点
				prefix.get(sum).next = next;
				// 将哈希表中所有中间节点的前缀和删掉
				int s = sum - head.val;
				while (s != sum) {
					ListNode node = prefix.get(s);
					prefix.remove(s);
					s -= node.val;
				}
			} else {
				prefix.put(sum, head);
			}
			head = next;
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
