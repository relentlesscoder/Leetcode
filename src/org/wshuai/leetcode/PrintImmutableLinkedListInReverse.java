package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 11/24/19.
 * #1265 https://leetcode.com/problems/print-immutable-linked-list-in-reverse/
 */
public class PrintImmutableLinkedListInReverse {

	// O(1) space
	public void printLinkedListInReverse(ImmutableListNode head) {
		if(head == null){
			return;
		}
		printLinkedListInReverse(head.getNext());
		head.printValue();
	}

	// O(n) space
	public void printLinkedListInReverseStack(ImmutableListNode head) {
		Stack<ImmutableListNode> stack = new Stack<>();
		ImmutableListNode cur = head;
		while(cur != null){
			stack.push(cur);
			cur = cur.getNext();
		}
		while(!stack.isEmpty()){
			stack.pop().printValue();
		}
	}
}

/**
* //This is the ImmutableListNode's API interface.
* //You should not implement it, or speculate about its implementation.
*/
interface ImmutableListNode {
	public void printValue(); // print the value of this node.
	public ImmutableListNode getNext(); // return the next node.
}
