package org.wshuai.leetcode;

/**
 * Created by Wei on 10/10/2016.
 * #0082 https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class RemoveDuplicatesFromSortedListII {
	// time O(n)
	public LinkedListNode deleteDuplicates(LinkedListNode head) {
		LinkedListNode root = new LinkedListNode(0), cur = head, prev = root;
		root.next = head;
		while(cur != null){
			if(cur.next != null && cur.val == cur.next.val){
				int val = cur.val;
				while(cur != null && cur.val == val){
					cur = cur.next;
				}
				prev.next = cur;
			}else{
				prev = cur;
				cur = cur.next;
			}
		}
		return root.next;
	}
}
