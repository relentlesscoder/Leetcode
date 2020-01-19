package org.wshuai.leetcode;

/**
 * Created by Wei on 08/20/2016.
 * #0148 https://leetcode.com/problems/sort-list/
 */
public class SortList {
	// time O(n*log(n))
	public LinkedListNode sortList(LinkedListNode head) {
		if(head == null || head.next == null){
			return head;
		}
		LinkedListNode fast = head, slow = head, prev = null;
		while(fast != null && fast.next != null){
			fast = fast.next.next;
			prev = slow;
			slow = slow.next;
		}
		prev.next = null;
		LinkedListNode right = sortList(slow);
		LinkedListNode left = sortList(head);
		return merge(right, left);
	}

	private LinkedListNode merge(LinkedListNode l1, LinkedListNode l2){
		LinkedListNode root = new LinkedListNode(0), cur = root;
		while(l1 != null || l2 != null){
			int v1 = l1 == null ? Integer.MAX_VALUE : l1.val;
			int v2 = l2 == null ? Integer.MAX_VALUE : l2.val;
			LinkedListNode next = null;
			if(v1 < v2){
				next = l1;
				l1 = l1.next;
			}else{
				next = l2;
				l2 = l2.next;
			}
			next.next = null;
			cur.next = next;
			cur = next;
		}
		return root.next;
	}
}
