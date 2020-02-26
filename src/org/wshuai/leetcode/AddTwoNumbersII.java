package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 01/07/2017.
 * #0445 https://leetcode.com/problems/add-two-numbers-ii/
 */
public class AddTwoNumbersII {
	// time O(n), space O(n)
	public LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
		Stack<Integer> num1 = new Stack<>(), num2 = new Stack<>();
		LinkedListNode cur = l1;
		while(cur != null){
			num1.push(cur.val);
			cur = cur.next;
		}
		cur = l2;
		while(cur != null){
			num2.push(cur.val);
			cur = cur.next;
		}
		int sum = 0, carry = 0;
		LinkedListNode next = null;
		while(!num1.isEmpty() || !num2.isEmpty() || carry > 0){
			int d1 = num1.isEmpty() ? 0 : num1.pop();
			int d2 = num2.isEmpty() ? 0 : num2.pop();
			sum = d1 + d2 + carry;
			cur = new LinkedListNode(sum % 10);
			cur.next = next;
			next = cur;
			carry = sum / 10;
		}
		return next;
	}
}
