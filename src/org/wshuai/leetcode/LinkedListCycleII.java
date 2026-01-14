package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0142 https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycleII {

	// time O(n), space O(1)
	public ListNode detectCycle(ListNode head) {
		// 假设进环前的路程为 a，环长为 b。设慢指针走了 x 步时，快慢指针相遇，
		// 此时快指针走了 2x步。显然 2x-x=nb（快指针比慢指针多走了 n 圈），
		// 即 x=nb。也就是说慢指针总共走过的路程是 nb，但这 nb 当中，实际上包
		// 含了进环前的一个小 a，因此慢指针在环中只走了 nb-a 步，它还得再往前
		// 走 a 步，才是完整的 n 圈。所以，我们让头节点和慢指针同时往前走，当
		// 他俩相遇时，就走过了最后这 a 步。
		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				while (slow != head) {
					slow = slow.next;
					head = head.next;
				}
				return slow;
			}
		}
		return null;
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
