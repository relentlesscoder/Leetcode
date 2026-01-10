package org.wshuai.leetcode;

/**
 * Created by Wei on 12/15/2019.
 * #1290 https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class ConvertBinaryNumberInALinkedListToInteger {

	// time O(n), space O(1)
    public int getDecimalValue(ListNode head) {
        int res = 0;
        while (head != null) {
            res = (res << 1) + head.val;
            head = head.next;
        }
        return res;
    }

    /**
     * Definition for singly-linked list.
     **/
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
