package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2019.
 * #0708 https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/
 */
public class InsertIntoASortedCircularLinkedList {

	// time O(n)
	public LinkedListNode insert(LinkedListNode head, int insertVal) {
		LinkedListNode node = new LinkedListNode(insertVal), cur = head;
		if(head == null){
			node.next = node;
			return node;
		}
		// find the last (max) node
		while(cur.next != head && cur.val <= cur.next.val){
			cur = cur.next;
		}
		// only if less than max value, loop again find the insertion point
		if(insertVal < cur.val){
			while(cur.next.val < insertVal){
				cur = cur.next;
			}
		}
		node.next = cur.next;
		cur.next = node;
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
