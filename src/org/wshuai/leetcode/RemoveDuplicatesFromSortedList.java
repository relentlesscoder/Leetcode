package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/2016.
 * #0083 https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class RemoveDuplicatesFromSortedList {
	// time O(n)
	public LinkedListNode deleteDuplicates(LinkedListNode head) {
		LinkedListNode cur = head;
		while(cur != null){
			while(cur.next != null && cur.val == cur.next.val){
				cur.next = cur.next.next;
			}
			cur = cur.next;
		}
		return head;
	}
}
