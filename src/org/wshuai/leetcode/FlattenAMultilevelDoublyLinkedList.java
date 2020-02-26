package org.wshuai.leetcode;

/**
 * Created by Wei on 09/22/2019.
 * #0430 https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 */
public class FlattenAMultilevelDoublyLinkedList {
	// time O(n)
	public DoublyLinkedListNode flatten(DoublyLinkedListNode head) {
		if(head == null){
			return head;
		}
		dfs(head);
		return head;
	}

	private DoublyLinkedListNode dfs(DoublyLinkedListNode head){
		DoublyLinkedListNode cur = head;
		while(cur != null){
			if(cur.child != null){
				DoublyLinkedListNode child = cur.child;
				DoublyLinkedListNode next = cur.next;
				cur.next = cur.child;
				child.prev = cur;
				cur.child = null;
				DoublyLinkedListNode tail = dfs(child);
				if(next != null){
					tail.next = next;
					next.prev = tail;
				}
				cur = tail;
			}
			if(cur.next == null){
				return cur;
			}
			cur = cur.next;
		}
		return null;
	}
}
