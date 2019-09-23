package org.wshuai.leetcode;

/**
 * Definition for singly-linked list.
 */
public class LinkedListNode {
	public int val;
	public LinkedListNode next;

	public LinkedListNode() {

	}

	public LinkedListNode(int _val) {
		this.val = _val;
		this.next = null;
	}

	public LinkedListNode(int _val, LinkedListNode _next) {
		val = _val;
		next = _next;
	}
}
