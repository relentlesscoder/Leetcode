package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2016.
 * #0086 https://leetcode.com/problems/partition-list/
 */
public class PartitionList {
	// O(n)
	public LinkedListNode partition(LinkedListNode head, int x) {
		LinkedListNode lt = new LinkedListNode(0), gte = new LinkedListNode(0), cur1 = lt, cur2 = gte;
		while(head != null){
			if(head.val < x){
				cur1.next = head;
				cur1 = cur1.next;
			}else{
				cur2.next = head;
				cur2 = cur2.next;
			}
			head = head.next;
		}
		cur2.next = null;
		cur1.next = gte.next;
		return lt.next;
	}
}
