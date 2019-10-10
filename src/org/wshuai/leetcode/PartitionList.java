package org.wshuai.leetcode;

/**
 * Created by Wei on 11/8/16.
 * #86 https://leetcode.com/problems/partition-list/
 */
public class PartitionList {
	//O(n)
	public LinkedListNode partition(LinkedListNode head, int x) {
		LinkedListNode left = new LinkedListNode(-1);
		LinkedListNode right = new LinkedListNode(-1);
		LinkedListNode lcurr = left;
		LinkedListNode rcurr = right;
		while (head != null) {
			if (head.val < x) {
				LinkedListNode node = new LinkedListNode(head.val);
				lcurr.next = node;
				lcurr = node;
			} else {
				LinkedListNode node = new LinkedListNode(head.val);
				rcurr.next = node;
				rcurr = node;
			}
			head = head.next;
		}
		if (left.next == null) {
			return right.next;
		} else {
			lcurr.next = right.next;
			return left.next;
		}
	}
}
