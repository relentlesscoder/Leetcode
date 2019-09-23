package org.wshuai.leetcode;

/**
 * Created by Wei on 8/9/15.
 * #2 https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {
	public static LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
		int sum = l1.val + l2.val;
		int carry = sum / 10;
		int remainder = sum % 10;
		LinkedListNode current = new LinkedListNode(remainder);
		LinkedListNode root = current;
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
			//no need to add one more node
			if (l1 == null && l2 == null && carry == 0) {
				break;
			}
			sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
			carry = sum / 10;
			remainder = sum % 10;
			current.next = new LinkedListNode(remainder);
			current = current.next;
		}

		return root;
	}
}
