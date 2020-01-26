package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0237 https://leetcode.com/problems/delete-node-in-a-linked-list/
 */
public class DeleteNodeInALinkedList {
	// time O(n)
	public void deleteNode(LinkedListNode node) {
		while(node.next.next != null){
			node.val = node.next.val;
			node = node.next;
		}
		node.val = node.next.val;
		node.next = null;
		return;
	}
}
