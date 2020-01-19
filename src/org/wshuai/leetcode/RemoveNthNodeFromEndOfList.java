package org.wshuai.leetcode;

/**
 * Created by Wei on 01/04/2020.
 * #0019 https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {
	public LinkedListNode removeNthFromEnd(LinkedListNode head, int n) {
		int res = removeNode(head, n);
		return res == n ? head.next : head;
	}

	private int removeNode(LinkedListNode cur, int n){
		if(cur.next == null){
			return 1;
		}
		int num = removeNode(cur.next, n);
		if(num == n){
			cur.next = cur.next.next;
		}
		return num + 1;
	}
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
