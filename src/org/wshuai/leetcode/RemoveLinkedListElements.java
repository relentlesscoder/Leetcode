package org.wshuai.leetcode;

/**
 * Created by Wei on 01/22/2020.
 * #0203 https://leetcode.com/problems/happy-number/
 */
public class RemoveLinkedListElements {
	// time O(n)
	public LinkedListNode removeElements(LinkedListNode head, int val) {
		LinkedListNode root = new LinkedListNode(0),
				cur = head, prev = root;
		root.next = cur;
		while(cur != null){
			if(cur.val == val){
				prev.next = cur.next;
			}else{
				prev = cur;
			}
			cur = cur.next;
		}
		return root.next;
	}
}
