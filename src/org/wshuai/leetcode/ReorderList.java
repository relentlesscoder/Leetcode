package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 10/21/2016.
 * #0143 https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {
	// time O(n), space O(1)
	public void reorderList(LinkedListNode head) {
		if (head == null) {
			return;
		}

		// Find the middle
		LinkedListNode fast = head;
		LinkedListNode slow = head;
		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
			if (fast != null) {
				fast = fast.next;
			}
		}

		// Split the list
		LinkedListNode it = head;
		while (it.next != slow) {
			it = it.next;
		}
		it.next = null;

		// Reverse the 2nd half
		LinkedListNode prev = null;
		LinkedListNode curr = slow;
		while (curr != null) {
			LinkedListNode nxt = curr.next;
			curr.next = prev;
			prev = curr;
			curr = nxt;
		}

		// Reorder
		LinkedListNode left = head;
		LinkedListNode right = prev;
		while (right != null) {
			LinkedListNode nxtLeft = left.next;
			LinkedListNode nxtRight = right.next;
			left.next = right;
			right.next = nxtLeft;
			left = nxtLeft;
			right = nxtRight;
		}
	}

	// time O(n), space O(n)
	public void reorderListStack(LinkedListNode head) {
		if(head == null){
			return;
		}
		Stack<LinkedListNode> stack = new Stack<>();
		LinkedListNode cur = head;
		while(cur != null){
			stack.push(cur);
			cur = cur.next;
		}
		cur = head;
		while(!stack.isEmpty() && cur != stack.peek()){
			LinkedListNode tail = stack.peek();
			if(cur.next == tail){
				break;
			}
			stack.pop();
			LinkedListNode next = cur.next;
			cur.next = tail;
			tail.next = next;
			cur = next;
		}
		stack.peek().next = null;
	}
}
