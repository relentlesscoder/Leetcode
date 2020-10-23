package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2015.
 * #0002 https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

	// time O(m + n)
	public LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
		LinkedListNode root = new LinkedListNode(-1);
		LinkedListNode cur = root;
		int sum = 0;
		while(l1 != null || l2 != null || sum > 0){
			int num1 = l1 == null ? 0 : l1.val;
			int num2 = l2 == null ? 0 : l2.val;
			sum = num1 + num2 + sum;
			cur.next = new LinkedListNode(sum % 10);
			cur = cur.next;
			l1 = l1 == null ? l1 : l1.next;
			l2 = l2 == null ? l2 : l2.next;
			sum /= 10;
		}
		return root.next;
	}
}
