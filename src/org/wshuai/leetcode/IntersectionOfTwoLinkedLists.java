package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #160 https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists {
	public LinkedListNode getIntersectionNode(LinkedListNode headA, LinkedListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		LinkedListNode x = headA;
		LinkedListNode y = headB;
		boolean af = true;
		boolean bf = true;
		while (x != null && y != null) {
			if (x == y) {
				return x;
			}

			if (x.next == null && af) {
				x = headB;
				af = false;
			} else {
				x = x.next;
			}

			if (y.next == null && bf) {
				y = headA;
				bf = false;
			} else {
				y = y.next;
			}
		}
		return null;
	}
}
