package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #141 https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {
	public boolean hasCycle(LinkedListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		LinkedListNode s = head.next;
		LinkedListNode f = head.next.next;
		while (s != null && f != null) {
			if (s == f) {
				return true;
			} else if (f.next == null) {
				return false;
			} else {
				s = s.next;
				f = f.next.next;
			}
		}
		return false;
	}
}
