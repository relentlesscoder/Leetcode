package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2023.
 * #2058 https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
 */
public class FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {

	// time O(n), space O(1)
	public int[] nodesBetweenCriticalPoints(ListNode head) {
		int min = Integer.MAX_VALUE, index = -1, firstLocal = -1, prevLocal = -1;
		ListNode currNode = head, prevNode = null;
		while (currNode != null) {
			index++;
			boolean isCritical = prevNode != null && currNode.next != null
					&& ((prevNode.val > currNode.val && currNode.next.val > currNode.val)
					|| (prevNode.val < currNode.val && currNode.next.val < currNode.val));
			if (isCritical) {
				if (prevLocal == -1) {
					firstLocal = index;
				} else {
					min = Math.min(min, index - prevLocal);
				}
				prevLocal = index;
			}
			prevNode = currNode;
			currNode = currNode.next;
		}
		min = (min == Integer.MAX_VALUE ? -1 : min);
		int max = (firstLocal != -1 && firstLocal != prevLocal ? prevLocal - firstLocal : -1);
		return new int[]{min, max};
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
