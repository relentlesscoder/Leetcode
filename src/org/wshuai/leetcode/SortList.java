package org.wshuai.leetcode;

/**
 * Created by Wei on 8/20/16.
 * #148 https://leetcode.com/problems/sort-list/
 */
public class SortList {
	public static LinkedListNode sortList(LinkedListNode head) {
		if (head != null && head.next != null) {
			if (head.next.next == null) {
				if (head.val > head.next.val) {
					int temp = head.val;
					head.val = head.next.val;
					head.next.val = temp;
				}
				return head;
			} else {
				LinkedListNode curr = head;
				LinkedListNode mid = head;
				while (curr != null && curr.next != null) {
					curr = curr.next.next;
					mid = mid.next;
				}
				LinkedListNode nxt = mid.next;
				mid.next = null;
				LinkedListNode l = sortList(head);
				LinkedListNode r = sortList(nxt);
				MergeTwoSortedList mt = new MergeTwoSortedList();
				return mt.mergeTwoLists(l, r);
			}
		} else {
			return head;
		}
	}
}
