package org.wshuai.leetcode;

/**
 * Created by Wei on 06/14/2020.
 * #1474 https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/
 */
public class DeleteNNodesAfterMNodesOfALinkedList {

	// time O(n)
	public LinkedListNode deleteNodes(LinkedListNode head, int m, int n) {
		LinkedListNode root = new LinkedListNode(-1), cur = head, prev = root;
		root.next = head;
		int x = m, y = n;
		while(cur != null){
			while(x-- > 0 && cur != null){
				prev = cur;
				cur = cur.next;
			}
			while(y-- > 0 && cur != null){
				cur = cur.next;
			}
			if(prev != null){
				prev.next = cur;
			}
			x = m;
			y = n;
		}
		return root.next;
	}
}
