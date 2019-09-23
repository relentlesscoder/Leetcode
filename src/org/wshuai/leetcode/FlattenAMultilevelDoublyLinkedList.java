package org.wshuai.leetcode;

/**
 * Created by Wei on 9/22/19.
 * #430 https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 */
public class FlattenAMultilevelDoublyLinkedList {
	public DoublyLinkedListNode flatten(DoublyLinkedListNode head) {
		DoublyLinkedListNode curr = head;
		flattenUtil(curr);
		return head;
	}

	private DoublyLinkedListNode flattenUtil(DoublyLinkedListNode curr){
		while(curr != null){
			if(curr.child != null){
				DoublyLinkedListNode next = curr.next;
				DoublyLinkedListNode child = curr.child;
				curr.next = curr.child;
				child.prev = curr;
				curr.child = null;
				DoublyLinkedListNode tail = flattenUtil(child);
				if(next != null){
					tail.next = next;
					next.prev = tail;
				}
				curr = tail;
			}
			if(curr.next == null){
				return curr;
			}
			curr = curr.next;
		}
		return null;
	}
}
