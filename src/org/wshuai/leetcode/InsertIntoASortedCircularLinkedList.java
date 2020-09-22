package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2019.
 * #0708 https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/
 */
public class InsertIntoASortedCircularLinkedList {

	// time O(n)
	public LinkedListNode insert(LinkedListNode head, int insertVal) {
		LinkedListNode insertNode = new LinkedListNode(insertVal);
		if(head == null){
			insertNode.next = insertNode;
			return insertNode;
		}
		LinkedListNode cur = head, prev = null;
		// find the last (max) node
		while(cur.next != head && cur.val <= cur.next.val){
			cur = cur.next;
		}
		// if value is larger than the max node, add it to tail
		if(insertVal >= cur.val){
			insertNode.next = cur.next;
			cur.next = insertNode;
			return head;
		}
		// find the insertion point
		prev = cur;
		cur = cur.next;
		while(cur.val <= cur.next.val && insertVal > cur.val){
			prev = cur;
			cur = cur.next;
		}
		insertNode.next = cur;
		prev.next = insertNode;
		return head;
	}

	// time O(n)
	public LinkedListNode insertMinMax(LinkedListNode head, int insertVal) {
		if(head == null){
			LinkedListNode root = new LinkedListNode(insertVal);
			root.next = root;
			return root;
		}
		LinkedListNode min = head, max = head, cur = head.next;
		while(cur != head){
			min = (cur.val < min.val) ? cur : min;
			max = (cur.val >= max.val) ? cur : max;
			cur = cur.next;
		}
		if(insertVal < min.val || insertVal > max.val){
			LinkedListNode insert = new LinkedListNode(insertVal, min);
			max.next = insert;
		}else{
			cur = min;
			while(true){
				if(cur.val <= insertVal && cur.next.val >= insertVal){
					LinkedListNode insert = new LinkedListNode(insertVal, cur.next);
					cur.next = insert;
					break;
				}
				cur = cur.next;
			}
		}
		return head;
	}
}
