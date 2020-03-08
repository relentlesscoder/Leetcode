package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 09/24/2016.
 * #0369 https://leetcode.com/problems/plus-one-linked-list/
 */
public class PlusOneLinkedList {
	// time O(n), space O(n)
	public LinkedListNode plusOne(LinkedListNode head) {
		if(head == null){
			return null;
		}
		Stack<LinkedListNode> stack = new Stack<>();
		LinkedListNode cur = head;
		int carry = 1;
		while(cur != null){
			stack.push(cur);
			cur = cur.next;
		}
		while(!stack.isEmpty()){
			LinkedListNode node = stack.pop();
			int sum = node.val + carry;
			node.val = sum % 10;
			carry = sum / 10;
		}
		if(carry > 0){
			LinkedListNode root = new LinkedListNode(carry);
			root.next = head;
			head = root;
		}
		return head;
	}
}
