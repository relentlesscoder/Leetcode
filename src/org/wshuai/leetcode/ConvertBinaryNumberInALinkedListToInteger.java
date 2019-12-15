package org.wshuai.leetcode;

/**
 * Created by Wei on 12/15/2019.
 * #1290 https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class ConvertBinaryNumberInALinkedListToInteger {
	public int getDecimalValue(LinkedListNode head) {
		LinkedListNode cur = head;
		int res = 0;
		while(cur != null){
			res = (res << 1) + cur.val;
			cur = cur.next;
		}
		return res;
	}
}
