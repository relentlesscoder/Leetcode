package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0141 https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {
	// time O(n)
	public boolean hasCycle(LinkedListNode head) {
		if(head == null){
			return false;
		}
		LinkedListNode fast = head, slow = head;
		while(fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
			if(fast == slow){
				return true;
			}
		}
		return false;
	}
}
